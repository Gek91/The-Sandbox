import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Write {

	
	public static void main(String[] args) {
		
		writeBasic();
	}
	
	private static void writeBasic() {
		
		String html = ""
				+ " <html>"
				+ "		<head>"
				+ "			<title>First parse</title>"
				+ "		</head>"
				+ "	"
				+ "		<body>"
				+ " 		<div>"
				+ "				<p id=\"par\">Parsed HTML into a doc.</p>"
				+ "				<p class=\"myclass\"> second paragraph </p>"
				+ " 			<a href=\"https://www.w3schools.com\">This is a link</a>"
				+ " 		</div>"
				+ "		</body>"
				+ "</html>";
		
		
		Document doc = Jsoup.parse(html);
		
		Element idElem = doc.getElementById("par");
		
		//Att attribute title with value 'myvalue' to the element
		idElem.attr("title", "myValue");
		idElem.addClass("myclass");
		System.out.println(idElem);
		
		Element div = doc.select("div").first();
		div.html("<p> mid </p>");
		div.prepend("<p> first </p>");
		div.append("<p> last </p>");
		div.wrap("<div></div>");
		System.out.println(div);

		
		Element firstP = div.select("p").first();
		firstP.text(" text");
		firstP.prepend("new");
		firstP.append("!!");
		System.out.println(div);
	}
}
