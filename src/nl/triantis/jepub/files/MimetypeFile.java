package nl.triantis.jepub.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import nl.triantis.jepub.book.Book;

public class MimetypeFile implements EpubFile{
	
	private Book book;
	
	public MimetypeFile(Book book) {
		this.book = book;
	}

	@Override
	public void create() {
		File file = book.getMimetypeFile();
		
		try (FileWriter fw = new FileWriter(file, false); PrintWriter pw = new PrintWriter(fw)) {
			pw.print("application/epub+zip");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
