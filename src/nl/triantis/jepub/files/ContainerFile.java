package nl.triantis.jepub.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import nl.triantis.jepub.Book;
import nl.triantis.jepub.namespace.NamespaceList;

public class ContainerFile implements EpubFile{
	
	private Book book;
	
	public ContainerFile(Book book) {
		this.book = book;
	}

	@Override
	public void create() {
		File file = book.getContainerFile();
		
		try (FileOutputStream out = new FileOutputStream(file)){
			Element container = new Element("container", NamespaceList.container);
			container.setAttribute("version", "1.0");
			
			Element rootfiles = new Element("rootfiles", NamespaceList.container);
			
			Element rootfile = new Element("rootfile", NamespaceList.container);
			rootfile.setAttribute("full-path", "OEBPS/content.opf");
			rootfile.setAttribute("media-type", "application/oebps-package+xml");
			
			rootfiles.addContent(rootfile);
			container.addContent(rootfiles);
			
			Document doc = new Document(container);
			
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, out);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
