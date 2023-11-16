package nl.triantis.jepub;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import nl.triantis.jepub.zip.Zip;

public class Main {
	
	/* > Book
	 * 		> META-INF
	 * 			container.xml
	 * 		> OEBPS
	 * 			> Images
	 * 			> Styles
	 * 				page_styles.css
	 * 				stylesheet.css
	 * 			> Text
	 * 			content.opf
	 * 			toc.ncx
	 * 		mimetype
	 */
	
	private static String export_directory = "export";
	private static String import_directory = "import";
	
	public static void main(String[] args) throws IOException {
		String author = "Danick Triantis";
		String rights = "All rights reserved";
		String publisher = "Triantis Publishing House";
		String title = "Test";
		String date = "11-11-2023";
		String language = "en-UK";
		String isbn = "9781780622293";
		String description = "A book generated with JePUB by Danick Triantis";
		String filename = title + " - " + author;
		
		String cover = import_directory + File.separator + "cover.jpg";
		BufferedImage coverImage = ImageIO.read(new File(cover));
		
		Book book = new Book(export_directory + File.separator + filename);
		book.setAuthor(author);	
		book.setRights(rights);
		book.setPublisher(publisher);
		book.setTitle(title);
		book.setDate(date);
		book.setLanguage(language);
		book.setIsbn(isbn);
		book.setDescription(description);
		book.setFileName(filename);
		book.setCoverImage(coverImage);
		
		book.createDirectories();
		book.createFiles();
		
		Zip zip = new Zip(book, export_directory, ".epub");
		zip.zipBook();
	}
	
	private static void importFiles() {
		File importDirectory = new File(import_directory + File.separator);
		
		for (File f : importDirectory.listFiles()) {
			String fileName = f.getName();
			
			if(fileName.contains("cover")) {
				
			}
		}
	}

}
