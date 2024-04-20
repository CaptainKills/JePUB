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

public class TextpageFile implements EpubFile {

	Book book;
	
	public TextpageFile(Book book) {
		this.book = book;
	}
	
	@Override
	public void create() {
//		for(File f : ) {
//			
//		}
		
		
		File file = new File(book.getTextDirectory().getPath() + File.separator + ".html");
		try (FileOutputStream out = new FileOutputStream(file)){
			Element textpage = generateTextpage();
			Document doc = new Document(textpage);
			
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, out);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private Element generateTextpage() {
		Element textpage = new Element("html", NamespaceList.xhtml);
		
		Element head = generateHead();
		textpage.addContent(head);
		
		Element body = generateBody();
		textpage.addContent(body);
		
		return textpage;
	}
	
	private Element generateHead() {
		Element head = new Element("head", NamespaceList.xhtml);
		
		Element title = new Element("title", head.getNamespace());
		title.setText(book.getTitle());
		head.addContent(title);
		
		Element meta = new Element("meta", head.getNamespace());
		meta.setAttribute("http-equiv", "Content-Type");
		meta.setAttribute("content", "text/html; charset=utf-8");
		head.addContent(meta);
		
		Element stylesheet = new Element("link", head.getNamespace());
		stylesheet.setAttribute("href", "../Styles/stylesheet.css");
		stylesheet.setAttribute("rel", "stylesheet");
		stylesheet.setAttribute("type", "text/css");
		head.addContent(stylesheet);
		
		Element pagestyles = new Element("link", head.getNamespace());
		pagestyles.setAttribute("href", "../Styles/page_styles.css");
		pagestyles.setAttribute("rel", "stylesheet");
		pagestyles.setAttribute("type", "text/css");
		head.addContent(pagestyles);
		
		return head;
	}
	
	private Element generateBody() {
		Element body = new Element("body", NamespaceList.xhtml);
		
		return body;
	}

}
