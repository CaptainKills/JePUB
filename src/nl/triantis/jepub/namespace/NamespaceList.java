package nl.triantis.jepub.namespace;

import org.jdom2.Namespace;

public class NamespaceList {
	
	public static Namespace container = Namespace.getNamespace("urn:oasis:names:tc:opendocument:xmlns:container");
	
	public static Namespace dcterms = Namespace.getNamespace("dcterms", "http://purl.org/dc/terms/");
	public static Namespace calibre = Namespace.getNamespace("calibre", "http://calibre.kovidgoyal.net/2009/metadata");
	public static Namespace xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
	public static Namespace opf = Namespace.getNamespace("opf", "http://www.idpf.org/2007/opf");
	public static Namespace dc = Namespace.getNamespace("dc", "http://purl.org/dc/elements/1.1/");
	
	public static Namespace ncx = Namespace.getNamespace("http://www.daisy.org/z3986/2005/ncx/");
	public static Namespace opf_uri = Namespace.getNamespace(NamespaceList.opf.getURI());
	
	public static Namespace xhtml = Namespace.getNamespace("http://www.w3.org/1999/xhtml");
	public static Namespace svg = Namespace.getNamespace("http://www.w3.org/2000/svg");
	public static Namespace xlink = Namespace.getNamespace("xlink", "http://www.w3.org/1999/xlink");
}
