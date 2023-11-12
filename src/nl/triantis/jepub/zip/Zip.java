package nl.triantis.jepub.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import nl.triantis.jepub.Book;

public class Zip {
	
	private Book book;
	private String fileExtension;
	
	public Zip(Book book, String fileExtension) {
		this.book = book;
		this.fileExtension = fileExtension;
	}
	
	public void zipBook() {
		try (FileOutputStream fos = new FileOutputStream(book.getBookDirectory() + File.separator + book.getFileName() + fileExtension, false);
				ZipOutputStream zos = new ZipOutputStream(fos);) {			
			zipDirectory(book.getBookDirectory(), zos);
			zos.closeEntry();
		} catch (ZipException e) {
			e.printStackTrace();       
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void zipDirectory(File dir, ZipOutputStream zos) throws ZipException, IOException {
		if(dir.listFiles().length == 0) {
			String filepath = book.getRelativePath(dir, book.getBookDirectory()) + File.separator;
			zos.putNextEntry(new ZipEntry(filepath));
			return;
		}
		
		for(File f : dir.listFiles()) {
			String filepath = book.getRelativePath(f, dir);
			if(filepath.compareTo(book.getFileName() + fileExtension) == 0) {
				continue;
			}
			
			if(f.isDirectory()) {
				zipDirectory(f, zos);
				continue;
			}
			
			zipFile(f, zos);
		}
	}
	
	private void zipFile(File file, ZipOutputStream zos) throws ZipException, IOException {
		FileInputStream fis = new FileInputStream(file.toString());
		
		String filepath = book.getRelativePath(file, book.getBookDirectory());
		zos.putNextEntry(new ZipEntry(filepath));
		
		byte[] buffer = new byte[1024];
		int len;
		
		while ((len = fis.read(buffer)) > 0) {
			zos.write(buffer, 0, len);
		}
		
		fis.close();
	}

}
