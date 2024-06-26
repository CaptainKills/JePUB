package nl.triantis.jepub.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import nl.triantis.jepub.book.Book;
import nl.triantis.jepub.defines.NamespaceList;

public class ContentFile implements EpubFile{
	
	private Book book;
	
	public ContentFile(Book book) {
		this.book = book;
	}

	@Override
	public void create() {
		File file = book.getContentFile();
		
		try (FileOutputStream out = new FileOutputStream(file)){
			Element root = new Element("package", NamespaceList.opf_uri);
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
		Element metadata = new Element("metadata", NamespaceList.opf_uri);
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
		Element manifest = new Element("manifest", NamespaceList.opf_uri);
		
		for(File f : book.getTextDirectory().listFiles()) {
			String filepath = book.getRelativePath(f, book.getOebpsDirectory());
			
			Element text = new Element("item", manifest.getNamespace());
			text.setAttribute("href", filepath);
			text.setAttribute("id", f.getName());
			text.setAttribute("media-type", "application/xhtml+xml");
			manifest.addContent(text);
		}
		
		for(File f : book.getImagesDirectory().listFiles()) {
			String filepath = book.getRelativePath(f, book.getOebpsDirectory());
			
			Element image = new Element("item", manifest.getNamespace());
			image.setAttribute("href", filepath);
			image.setAttribute("id", f.getName());
			image.setAttribute("media-type", "image/jpeg");
			manifest.addContent(image);
		}
		
		String filepath = book.getRelativePath(book.getPageStylesFile(), book.getOebpsDirectory());
		Element pagestyle = new Element("item", manifest.getNamespace());
		pagestyle.setAttribute("href", filepath);
		pagestyle.setAttribute("id", "page_css");
		pagestyle.setAttribute("media-type", "text/css");
		manifest.addContent(pagestyle);
		
		filepath = book.getRelativePath(book.getStylesheetFile(), book.getOebpsDirectory());
		Element stylesheet = new Element("item", manifest.getNamespace());
		stylesheet.setAttribute("href", filepath);
		stylesheet.setAttribute("id", "css");
		stylesheet.setAttribute("media-type", "text/css");
		manifest.addContent(stylesheet);
		
		filepath = book.getRelativePath(book.getTableOfContentsFile(), book.getOebpsDirectory());
		Element toc = new Element("item", manifest.getNamespace());
		toc.setAttribute("href", filepath);
		toc.setAttribute("id", "ncx");
		toc.setAttribute("media-type", "application/x-dtbncx+xml");
		manifest.addContent(toc);
		
		return manifest;
	}
	
	private Element generateSpine() {
		Element spine = new Element("spine", NamespaceList.opf_uri);
		spine.setAttribute("toc", "ncx");
		
		for(File f : book.getTextDirectory().listFiles()) {
			Element text = new Element("itemref", spine.getNamespace());
			text.setAttribute("idref", f.getName());
			spine.addContent(text);
		}
		
		return spine;
	}
	
	private Element generateGuide() {
		Element guide = new Element("guide", NamespaceList.opf_uri);
		
		Element cover = new Element("reference", guide.getNamespace());
		cover.setAttribute("type", "cover");
		cover.setAttribute("title", "Cover");
		cover.setAttribute("href", "Text/titlepage.xhtml");
		guide.addContent(cover);
		
		Element toc = new Element("reference", guide.getNamespace());
		toc.setAttribute("type", "toc");
		toc.setAttribute("title", "Table of Contents");
		toc.setAttribute("href", "");
		guide.addContent(toc);
		
		Element text = new Element("reference", guide.getNamespace());
		text.setAttribute("type", "text");
		text.setAttribute("title", "Text");
		text.setAttribute("href", "");
		guide.addContent(text);
		
		return guide;
	}
}
