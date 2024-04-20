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
		
		File bookDirectory = Paths.get(getBookDirectory()).toFile();
		if(!bookDirectory.exists()) {
			bookDirectory.mkdir();
		}
		
		File metaDirectory = Paths.get(getMetaDirectory()).toFile();
		if(!metaDirectory.exists()) {
			metaDirectory.mkdir();
		}
		
		File oebpsDirectory = Paths.get(getOebpsDirectory()).toFile();
		if(!oebpsDirectory.exists()) {
			oebpsDirectory.mkdir();
		}
		
		File imagesDirectory = Paths.get(getImagesDirectory()).toFile();
		if(!imagesDirectory.exists()) {
			imagesDirectory.mkdir();
		}
		
		File stylesDirectory = Paths.get(getStylesDirectory()).toFile();
		if(!stylesDirectory.exists()) {
			stylesDirectory.mkdir();
		}
		
		File textDirectory = Paths.get(getTextDirectory()).toFile();
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
	public String getBookDirectory() {
		return book_directory;
	}

	public String getMetaDirectory() {
		return meta_directory;
	}

	public String getOebpsDirectory() {
		return oebps_directory;
	}

	public String getImagesDirectory() {
		return images_directory;
	}

	public String getStylesDirectory() {
		return styles_directory;
	}

	public String getTextDirectory() {
		return text_directory;
	}

	// Getters: EPUB Files
	public File getContainerFile() {
		return container_file;
	}

	public File getPageStylesFile() {
		return page_styles_file;
	}

	public File getStylesheetFile() {
		return stylesheet_file;
	}

	public File getContentFile() {
		return content_file;
	}

	public File getTableOfContentsFile() {
		return toc_file;
	}

	public File getMimetypeFile() {
		return mimetype_file;
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
