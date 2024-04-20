package nl.triantis.jepub.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import nl.triantis.jepub.book.Book;

public class StylesheetFile implements EpubFile{
	
	private Book book;
	
	public StylesheetFile(Book book) {
		this.book = book;
	}

	@Override
	public void create() {
		StringBuilder builder = new StringBuilder();
		builder.append("");
		
		File file = book.getStylesheetFile();
		
		try (FileWriter fw = new FileWriter(file, false); PrintWriter pw = new PrintWriter(fw)) {
			pw.print(builder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
