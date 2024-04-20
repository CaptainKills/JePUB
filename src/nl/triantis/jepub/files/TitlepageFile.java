package nl.triantis.jepub.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import nl.triantis.jepub.book.Book;
import nl.triantis.jepub.defines.NamespaceList;

public class TitlepageFile implements EpubFile {

	private Book book;
	
	public TitlepageFile(Book book) {
		this.book = book;
	}
	
	@Override
	public void create() {
		try {
			String imagePath = book.getImagesDirectory() + File.separator + "cover.jpg";
			ImageIO.write(book.getCoverImage(), "jpg", new File(imagePath));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		File file = new File(book.getTextDirectory().getPath() + File.separator + "titlepage.xhtml");
		try (FileOutputStream out = new FileOutputStream(file)){
			Element titlepage = generateTitlepage();
			Document doc = new Document(titlepage);
			
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, out);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private Element generateTitlepage() {
		String imageWidth = Integer.toString(book.getCoverImage().getWidth());
		String imageHeight = Integer.toString(book.getCoverImage().getHeight());
		
		Element titlepage = new Element("html", NamespaceList.xhtml);
		
		Element head = new Element("head", titlepage.getNamespace());
		titlepage.addContent(head);
		
		Element title = new Element("title", head.getNamespace());
		title.setText("Cover");
		head.addContent(title);
		
		Element body = new Element("body", titlepage.getNamespace());
		titlepage.addContent(body);
		
		Element div = new Element("div", body.getNamespace());
		div.setAttribute("style", "text-align: center; padding: 0pt; margin: 0pt;");
		body.addContent(div);
		
		Element svg = new Element("svg", NamespaceList.svg);
		svg.addNamespaceDeclaration(NamespaceList.xlink);
		svg.setAttribute("version", "1.1");
		svg.setAttribute("width", "100%");
		svg.setAttribute("height", "100%");
		svg.setAttribute("viewBox", "0 0 " + imageWidth + " " + imageHeight);
		svg.setAttribute("preserveAspectRatio", "none");
		div.addContent(svg);
		
		Element image = new Element("image", svg.getNamespace());
		image.setAttribute("width", imageWidth);
		image.setAttribute("height", imageHeight);
		image.setAttribute("href", "../Images/cover.jpg", NamespaceList.xlink);
		svg.addContent(image);
		
		return titlepage;
	}

}
