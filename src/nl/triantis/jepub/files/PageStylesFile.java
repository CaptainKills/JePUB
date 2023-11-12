package nl.triantis.jepub.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import nl.triantis.jepub.Book;

public class PageStylesFile implements EpubFile{
	
	private Book book;
	
	public PageStylesFile(Book book) {
		this.book = book;
	}

	@Override
	public void create() {
		StringBuilder builder = new StringBuilder();
		builder.append("@page {\n");
		builder.append("\tmargin-bottom: 5pt;\n");
		builder.append("\tmargin-top: 5pt\n");
		builder.append("}\n");
		
		File file = book.getPageStylesFile();
		
		try (FileWriter fw = new FileWriter(file, false); PrintWriter pw = new PrintWriter(fw)) {
			pw.print(builder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
