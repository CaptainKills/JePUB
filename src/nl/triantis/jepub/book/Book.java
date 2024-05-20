package nl.triantis.jepub.book;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;

import nl.triantis.jepub.files.ContainerFile;
import nl.triantis.jepub.files.ContentFile;
import nl.triantis.jepub.files.MimetypeFile;
import nl.triantis.jepub.files.PageStylesFile;
import nl.triantis.jepub.files.StylesheetFile;
import nl.triantis.jepub.files.TableOfContentsFile;
import nl.triantis.jepub.files.TitlepageFile;

public class Book {
	
	private BookDirectory directory;
	
	private String author;
	private String rights;
	private String publisher;
	private String title;
	private String date;
	private String language;
	private String isbn;
	private String description;
	private String filename;
	
	private BufferedImage coverImage;
	
	public void createDirectories() {
		this.directory = new BookDirectory(filename);
		
		File bookDirectory = getBookDirectory();
		if(!bookDirectory.exists()) {
			bookDirectory.mkdir();
		}
		
		File metaDirectory = getMetaDirectory();
		if(!metaDirectory.exists()) {
			metaDirectory.mkdir();
		}
		
		File oebpsDirectory = getOebpsDirectory();
		if(!oebpsDirectory.exists()) {
			oebpsDirectory.mkdir();
		}
		
		File imagesDirectory = getImagesDirectory();
		if(!imagesDirectory.exists()) {
			imagesDirectory.mkdir();
		}
		
		File stylesDirectory = getStylesDirectory();
		if(!stylesDirectory.exists()) {
			stylesDirectory.mkdir();
		}
		
		File textDirectory = getTextDirectory();
		if(!textDirectory.exists()) {
			textDirectory.mkdir();
		}
	}
	
	public void createFiles() {
		// Epub Content
		TitlepageFile titlepage = new TitlepageFile(this);
		titlepage.create();
		
//		TextpageFile textpage = new TextpageFile(this);
//		textpage.create();
		
		// Epub Framework
		ContainerFile container = new ContainerFile(this);
		container.create();
		
		PageStylesFile pagestyles = new PageStylesFile(this);
		pagestyles.create();
		
		StylesheetFile stylesheet = new StylesheetFile(this);
		stylesheet.create();
		
		ContentFile content = new ContentFile(this);
		content.create();
		
		TableOfContentsFile toc = new TableOfContentsFile(this);
		toc.create();
		
		MimetypeFile mimetype = new MimetypeFile(this);
		mimetype.create();
	}
	
	public String getRelativePath(File file, File dir) {
		String filePath = file.toString();
		filePath = filePath.substring(dir.toString().length() + 1);
		filePath = filePath.replace("\\", "/");
		
		return filePath;
	}

	// Getters: EPUB Directories
	public File getBookDirectory() {
		return Paths.get(directory.bookDirectory).toFile();
	}

	public File getMetaDirectory() {
		return Paths.get(directory.metaDirectory).toFile();
	}

	public File getOebpsDirectory() {
		return Paths.get(directory.oebpsDirectory).toFile();
	}

	public File getImagesDirectory() {
		return Paths.get(directory.imagesDirectory).toFile();
	}

	public File getStylesDirectory() {
		return Paths.get(directory.stylesDirectory).toFile();
	}

	public File getTextDirectory() {
		return Paths.get(directory.textDirectory).toFile();
	}

	// Getters: EPUB Files
	public File getContainerFile() {
		return Paths.get(directory.containerFile).toFile();
	}

	public File getPageStylesFile() {
		return Paths.get(directory.pageStylesFile).toFile();
	}

	public File getStylesheetFile() {
		return Paths.get(directory.stylesheetFile).toFile();
	}

	public File getContentFile() {
		return Paths.get(directory.contentFile).toFile();
	}

	public File getTableOfContentsFile() {
		return Paths.get(directory.tocFile).toFile();
	}

	public File getMimetypeFile() {
		return Paths.get(directory.mimetypeFile).toFile();
	}

	// Getters & Setters: Book Parameters
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getFileName() {
		return filename;
	}

	public void setFileName(String filename) {
		this.filename = filename;
	}

	public BufferedImage getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(BufferedImage coverImage) {
		this.coverImage = coverImage;
	}
	
}
