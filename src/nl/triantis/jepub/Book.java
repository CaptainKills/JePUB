package nl.triantis.jepub;

import java.io.File;
import java.nio.file.Paths;

import nl.triantis.jepub.files.ContainerFile;
import nl.triantis.jepub.files.ContentFile;
import nl.triantis.jepub.files.MimetypeFile;
import nl.triantis.jepub.files.PageStylesFile;
import nl.triantis.jepub.files.StylesheetFile;
import nl.triantis.jepub.files.TableOfContentsFile;

public class Book {
	
	private File book_directory;
	private File meta_directory;
	private File oebps_directory;
	private File images_directory;
	private File styles_directory;
	private File text_directory;
	
	private File container_file;
	private File page_styles_file;
	private File stylesheet_file;
	private File content_file;
	private File toc_file;
	private File mimetype_file;
	
	private String author;
	private String rights;
	private String publisher;
	private String title;
	private String date;
	private String language;
	private String isbn;
	private String description;
	
	public Book(String book_directory) {
		this.book_directory = Paths.get(book_directory).toFile();
		
		this.meta_directory = Paths.get(book_directory + File.separator + "META-INF").toFile();
		this.oebps_directory = Paths.get(book_directory + File.separator + "OEBPS").toFile();
		this.images_directory = Paths.get(oebps_directory + File.separator + "Images").toFile();
		this.styles_directory = Paths.get(oebps_directory + File.separator + "Styles").toFile();
		this.text_directory = Paths.get(oebps_directory + File.separator + "Text").toFile();
		
		this.container_file = Paths.get(meta_directory + File.separator + "container.xml").toFile();
		this.page_styles_file = Paths.get(styles_directory + File.separator + "page_styles.css").toFile();
		this.stylesheet_file = Paths.get(styles_directory + File.separator + "stylesheet.css").toFile();
		this.content_file = Paths.get(oebps_directory + File.separator + "content.opf").toFile();
		this.toc_file = Paths.get(oebps_directory + File.separator + "toc.ncx").toFile();
		this.mimetype_file = Paths.get(book_directory + File.separator + "mimetype").toFile();
	}
	
	public void createDirectories() {
		if(!getBookDirectory().exists()) {
			getBookDirectory().mkdir();
		}
		
		if(!getMetaDirectory().exists()) {
			getMetaDirectory().mkdir();
		}
		
		if(!getOebpsDirectory().exists()) {
			getOebpsDirectory().mkdir();
		}
		
		if(!getImagesDirectory().exists()) {
			getImagesDirectory().mkdir();
		}
		
		if(!getStylesDirectory().exists()) {
			getStylesDirectory().mkdir();
		}
		
		if(!getTextDirectory().exists()) {
			getTextDirectory().mkdir();
		}
	}
	
	public void createFiles() {
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
		
		return filePath;
	}

	// Getters: EPUB Directories
	public File getBookDirectory() {
		return book_directory;
	}

	public File getMetaDirectory() {
		return meta_directory;
	}

	public File getOebpsDirectory() {
		return oebps_directory;
	}

	public File getImagesDirectory() {
		return images_directory;
	}

	public File getStylesDirectory() {
		return styles_directory;
	}

	public File getTextDirectory() {
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
	
}
