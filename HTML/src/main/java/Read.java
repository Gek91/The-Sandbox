import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Read {

	
	public static void main(String[] args) {
					
//		parseBasic();
//		parseFromURL();
//		parseFromFile();
//		elementsTraversing();
		selectorSyntax();
	}
	
	private static void parseBasic() {
	
		String html = ""
				+ " <html>"
				+ "		<head>"
				+ "			<title>First parse</title>"
				+ "		</head>"
				+ "	"
				+ "		<body>"
				+ "			<p id=\"par\">Parsed HTML into a doc.</p>"
				+ "			<p class=\"myclass\"> second paragraph </p>"
				+ " 		<a href=\"https://www.w3schools.com\">This is a link</a>"
				+ "		</body>"
				+ "</html>";
		
		
		Document doc = Jsoup.parse(html);
		
		String title = doc.title();
		System.out.println("Title :" + title);

		Element body = doc.body();
		System.out.println("Body : " + body.html());
		
		Element head = doc.head();
		System.out.println("Head : " + head.html());

		//By Id
		Element idElem = doc.getElementById("par");
		System.out.println("Element with id par : " + idElem.html());
		
		System.out.println("ELEMENTS:");
		Elements bodyElements = body.getAllElements();
		Iterator<Element> it = bodyElements.iterator();
		while(it.hasNext()) { //Returns 3 element : the whole body and the two <p>
			System.out.println(it.next().html());
		}
	
		//By Tag
		System.out.println("ELEMENTS With tag <a>:");
		Elements elementsWithTag = doc.getElementsByTag("a");
		it = elementsWithTag.iterator();
		while(it.hasNext()) { 
			System.out.println(it.next().html());
		}
		
		//By Class
		System.out.println("ELEMENTS With class 'myclass':");
		Elements elementsWithClass = doc.getElementsByClass("myclass");
		it = elementsWithClass.iterator();
		while(it.hasNext()) { 
			System.out.println(it.next().html());
		}
		
		//By Attribute
		System.out.println("ELEMENTS With attribute 'href':");
		Elements elementsWithAttribute = doc.getElementsByAttribute("href");
		it = elementsWithAttribute.iterator();
		while(it.hasNext()) { 
			System.out.println(it.next().html());
		}
	}
	
	private static void elementsTraversing() {
		
		String html = ""
				+ " <html>"
				+ "		<head>"
				+ "			<title>First parse</title>"
				+ "		</head>"
				+ "	"
				+ "		<body>"
				+ " 		<div id=\"outer\">"
				+ " 			<div id=\"par\" myattribute1=\"val1\" myattribute2=\"val2\">"
				+ "					<p id=\"inner1\">Parsed HTML into a doc.</p>"
				+ "					<p id=\"inner2\">Parsed HTML into a doc.</p>"
				+ "				</div>"
				+ "  			<span id=\"sib\">"
				+ "			</div>"
				+ "		</body>"
				+ "</html>";
		
		Document doc = Jsoup.parse(html);

		Element idElem = doc.getElementById("par");
		//element fields
		System.out.println("tag: " + idElem.tag());
		System.out.println("text: " + idElem.text());
		System.out.println("html: " + idElem.html());
		System.out.println("data: " + idElem.data());
		System.out.println("attributes: " + idElem.attributes().html());
		System.out.println("id: " + idElem.id());
		
		//Traversing 
		Element parent = idElem.parent();
		System.out.println("Parent : " + parent);
		Elements siblings = idElem.siblingElements();
		System.out.println("Siblings : " + siblings);
		Elements children = idElem.children();
		System.out.println("Children : " + children);

	}
	
	private static void selectorSyntax() {
		
		String html = ""
				+ " <html>"
				+ "		<head>"
				+ "			<title>First parse</title>"
				+ "		</head>"
				+ "	"
				+ "		<body>"
				+ " 		<div class='myclass'>"
				+ " 			<div id=\"par\" myattribute1=\"val1\" myattribute2=\"val2\">"
				+ "					<p id=\"inner1\">Parsed HTML into a doc.</p>"
				+ "					<p id=\"inner2\">Parsed HTML into a doc.</p>"
				+ "				</div>"
				+ "  			<span id=\"sib\">"
				+ "			</div>"
				+ " 		<a href=\\\"https://www.w3schools.com\\\">This is a link</a>\""
				+ "		</body>"
				+ "</html>";
		
		Document doc = Jsoup.parse(html);

		//CSS/JQuery selector
		Element elem = doc.select("div.myclass").first();
		System.out.println(elem);
		
		elem = doc.select("#sib").first();
		System.out.println(elem);
		
		elem = doc.select("[href]").first();
		System.out.println(elem);
	}
	
	private static void parseFromURL() {
		
		try {
		
		Document doc = Jsoup.connect("http://google.com").get();
		
		String title = doc.title();
		
		System.out.print(doc.html());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void parseFromFile() {
		
		try {
			
			File file = new File("Solving/resources/htmlExample.html");
	
			Document doc = Jsoup.parse(file, "UTF-8");
			
			System.out.print(doc.html());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}