package nl.triantis.jepub.book;

import java.io.File;

import nl.triantis.jepub.defines.PathList;

public class BookDirectory {
	
	// Book Directories
	public String bookDirectory;
	public String metaDirectory;
	public String oebpsDirectory;
	public String imagesDirectory;
	public String stylesDirectory;
	public String textDirectory;
	
	// Book Files
	public String containerFile;
	public String pageStylesFile;
	public String stylesheetFile;
	public String contentFile;
	public String tocFile;
	public String mimetypeFile;
	
	public BookDirectory(String fileName) {
		bookDirectory = createPath(PathList.exportDirectory, fileName);
		metaDirectory = createPath(bookDirectory, "META_INF");
		oebpsDirectory = createPath(bookDirectory, "OEBPS");
		imagesDirectory = createPath(oebpsDirectory, "Images");
		stylesDirectory = createPath(oebpsDirectory, "Styles");
		textDirectory = createPath(oebpsDirectory, "Text");
		
		containerFile = createPath(metaDirectory, "container.xml");
		pageStylesFile = createPath(stylesDirectory, "page_styles.css");
		stylesheetFile = createPath(stylesDirectory, "stylesheet.css");
		contentFile = createPath(oebpsDirectory, "content.opf");
		tocFile = createPath(oebpsDirectory, "toc.ncx");
		mimetypeFile = createPath(bookDirectory, "mimetype");
	}
	
	private static String createPath(String... paths) {
		StringBuilder builder = new StringBuilder();
		
		int i = 0;
		for(String path : paths) {
			builder.append(path);
			
			if(i < paths.length - 1) {
				builder.append(File.separator);
			}
			
			i++;
		}
		
		return builder.toString();
	}
}
