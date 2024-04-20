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

public class TableOfContentsFile implements EpubFile{
	
	private Book book;
	
	public TableOfContentsFile(Book book) {
		this.book = book;
	}

	@Override
	public void create() {
		File file = book.getTableOfContentsFile();
		
		try (FileOutputStream out = new FileOutputStream(file)){
			Element ncx = new Element("ncx", NamespaceList.ncx);
			ncx.setAttribute("unique-identifier", "uuid_id");
			ncx.setAttribute("version", "2005-1");
			
			Element head = generateHead();
			ncx.addContent(head);
			
			Element doctitle = new Element("docTitle", ncx.getNamespace());
			Element title = new Element("text", doctitle.getNamespace());
			title.setText(book.getTitle());
			doctitle.addContent(title);
			ncx.addContent(doctitle);
			
			Element navmap = generateNavmap();
			ncx.addContent(navmap);
			
			Document doc = new Document(ncx);
			
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, out);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private Element generateHead() {
		Element head = new Element("head", NamespaceList.ncx);
		
		Element uid = new Element("meta", head.getNamespace());
		uid.setAttribute("name", "dtb:uid");
		uid.setAttribute("content", "0");
		head.addContent(uid);
		
		Element depth = new Element("meta", head.getNamespace());
		depth.setAttribute("name", "dtb:depth");
		depth.setAttribute("content", "1");
		head.addContent(depth);
		
		Element generator = new Element("meta", head.getNamespace());
		generator.setAttribute("name", "dtb:generator");
		generator.setAttribute("content", "JePUB by Danick Triantis");
		head.addContent(generator);
		
		Element totalpagecount = new Element("meta", head.getNamespace());
		totalpagecount.setAttribute("name", "dtb:totalPageCount");
		totalpagecount.setAttribute("content", "0");
		head.addContent(totalpagecount);
		
		Element maxpagenumber = new Element("meta", head.getNamespace());
		maxpagenumber.setAttribute("name", "dtb:maxPageNumber");
		maxpagenumber.setAttribute("content", "0");
		head.addContent(maxpagenumber);
		
		return head;
	}
	
	private Element generateNavmap() {
		Element navmap = new Element("navMap", NamespaceList.ncx);
		
		int id = 0;
		for(File f : book.getTextDirectory().listFiles()) {
			Element navpoint = new Element("navPoint", navmap.getNamespace());
			navpoint.setAttribute("id", "navPoint-" + Integer.toString(id + 1));
			navpoint.setAttribute("playOrder", Integer.toString(id + 1));
			
			Element navlabel = new Element("navLabel", navmap.getNamespace());
			Element label = new Element("text", NamespaceList.ncx);
			label.setText("Chapter " + Integer.toString(id + 1));
			navlabel.addContent(label);
			navpoint.addContent(navlabel);
			
			String filepath = book.getRelativePath(f, book.getOebpsDirectory());
			Element content = new Element("content", navmap.getNamespace());
			content.setAttribute("src", filepath);
			navpoint.addContent(content);
			
			navmap.addContent(navpoint);
			id++;
		}
		
		return navmap;
	}
}
