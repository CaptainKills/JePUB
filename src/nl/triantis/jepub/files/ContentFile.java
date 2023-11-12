package nl.triantis.jepub.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import nl.triantis.jepub.Book;
import nl.triantis.jepub.namespace.NamespaceList;

public class ContentFile implements EpubFile{
	
	private Book book;
	
	public ContentFile(Book book) {
		this.book = book;
	}

	@Override
	public void create() {
		File file = book.getContentFile();
		
		try (FileOutputStream out = new FileOutputStream(file)){
			Element root = new Element("package");
			root.setNamespace(Namespace.getNamespace(NamespaceList.opf.getURI()));
			root.setAttribute("unique-identifier", "uuid_id");
			root.setAttribute("version", "2.0");
			
			Element metadata = generateMetadata();
			root.addContent(metadata);
			
			Element manifest = generateManifest();
			root.addContent(manifest);
			
			Element spine = generateSpine();
			root.addContent(spine);
			
			Element guide = generateGuide();
			root.addContent(guide);
			
			Document doc = new Document(root);
			
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, out);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private Element generateMetadata() {
		Element metadata = new Element("metadata");
		metadata.addNamespaceDeclaration(NamespaceList.dcterms);
		metadata.addNamespaceDeclaration(NamespaceList.calibre);
		metadata.addNamespaceDeclaration(NamespaceList.xsi);
		metadata.addNamespaceDeclaration(NamespaceList.opf);
		metadata.addNamespaceDeclaration(NamespaceList.dc);
		
		Element creator = new Element("creator", NamespaceList.dc);
		creator.setText(book.getAuthor());
		metadata.addContent(creator);
		
		Element rights = new Element("rights", NamespaceList.dc);
		rights.setText(book.getRights());
		metadata.addContent(rights);
		
		Element publisher = new Element("publisher", NamespaceList.dc);
		publisher.setText(book.getPublisher());
		metadata.addContent(publisher);
		
		Element contributor = new Element("contributor", NamespaceList.dc);
		contributor.setAttribute("scheme", "bkp", NamespaceList.opf);
		contributor.setText("JEPUB by Danick Triantis");
		metadata.addContent(contributor);
		
		Element title = new Element("title", NamespaceList.dc);
		title.setText(book.getTitle());
		metadata.addContent(title);
		
		Element date = new Element("date", NamespaceList.dc);
		date.setText(book.getDate());
		metadata.addContent(date);
		
		Element language = new Element("language", NamespaceList.dc);
		language.setText(book.getLanguage());
		metadata.addContent(language);
		
		Element isbn = new Element("identifier", NamespaceList.dc);
		isbn.setAttribute("id", "EbookISBN");
		isbn.setText(book.getIsbn());
		metadata.addContent(isbn);
		
		Element description = new Element("description", NamespaceList.dc);
		description.setText(book.getDescription());
		metadata.addContent(description);
		
		return metadata;
	}
	
	private Element generateManifest() {
		Element manifest = new Element("manifest");
		
		for(File f : book.getTextDirectory().listFiles()) {
			Element text = new Element("item");
			text.setAttribute("href", book.getRelativePath(f));
			text.setAttribute("id", f.getName());
			text.setAttribute("media-type", "application/xhtml+xml");
			manifest.addContent(text);
		}
		
		for(File f : book.getImagesDirectory().listFiles()) {
			Element image = new Element("item");
			image.setAttribute("href", book.getRelativePath(f));
			image.setAttribute("id", f.getName());
			image.setAttribute("media-type", "image/jpeg");
			manifest.addContent(image);
		}
		
		Element pagestyle = new Element("item");
		pagestyle.setAttribute("href", book.getRelativePath(book.getPageStylesFile()));
		pagestyle.setAttribute("id", "page_css");
		pagestyle.setAttribute("media-type", "text/css");
		manifest.addContent(pagestyle);
		
		Element stylesheet = new Element("item");
		stylesheet.setAttribute("href", book.getRelativePath(book.getStylesheetFile()));
		stylesheet.setAttribute("id", "css");
		stylesheet.setAttribute("media-type", "text/css");
		manifest.addContent(stylesheet);
		
		Element toc = new Element("item");
		toc.setAttribute("href", book.getRelativePath(book.getTableOfContentsFile()));
		toc.setAttribute("id", "ncx");
		toc.setAttribute("media-type", "application/x-dtbncx+xml");
		manifest.addContent(toc);
		
		return manifest;
	}
	
	private Element generateSpine() {
		Element spine = new Element("spine");
		spine.setAttribute("toc", "ncx");
		
		for(File f : book.getTextDirectory().listFiles()) {
			Element text = new Element("itemref");
			text.setAttribute("idref", f.getName());
			spine.addContent(text);
		}
		
		return spine;
	}
	
	private Element generateGuide() {
		Element guide = new Element("guide");
		
		Element cover = new Element("reference");
		cover.setAttribute("type", "cover");
		cover.setAttribute("title", "Cover");
		cover.setAttribute("href", "");
		guide.addContent(cover);
		
		Element titlepage = new Element("reference");
		titlepage.setAttribute("type", "text");
		titlepage.setAttribute("title", "Title Page");
		titlepage.setAttribute("href", "");
		guide.addContent(titlepage);
		
		Element toc = new Element("reference");
		toc.setAttribute("type", "toc");
		toc.setAttribute("title", "Table of Contents");
		toc.setAttribute("href", "");
		guide.addContent(toc);
		
		Element text = new Element("reference");
		text.setAttribute("type", "text");
		text.setAttribute("title", "Text");
		text.setAttribute("href", "");
		guide.addContent(text);
		
		return guide;
	}
}
