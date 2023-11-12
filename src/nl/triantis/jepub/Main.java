package nl.triantis.jepub;

import java.io.File;

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
	
	public static void main(String[] args) {
		String author = "Author";
		String rights = "All rights reserved";
		String publisher = "Publisher";
		String title = "EPUB Book";
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
		
		book.createDirectories();
		book.createFiles();
	}

}
