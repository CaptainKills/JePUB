package nl.triantis.jepub;

import java.io.File;

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
	
	public static void main(String[] args) {
		String author = "Danick Triantis";
		String rights = "All rights reserved";
		String publisher = "Triantis Publishing House";
		String title = "Test";
		String date = "11-11-2023";
		String language = "en-UK";
		String isbn = "9781780622293";
		String description = "A book generated with JePUB by Danick Triantis";
		
		Book book = new Book(export_directory + File.separator + "");
		book.setAuthor(author);	
		book.setRights(rights);
		book.setPublisher(publisher);
		book.setTitle(title);
		book.setDate(date);
		book.setLanguage(language);
		book.setIsbn(isbn);
		book.setDescription(description);
		book.setFileName(title + " - " + author);
		
		book.createDirectories();
		book.createFiles();
		
		Zip zip = new Zip(book, ".epub");
		zip.zipBook();
	}

}
