package xmlParser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public class DocNavigation {

	private static final String FILENAME = "example.xml";

	public static void main(String[] args) {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(new File(FullTreePrint.class.getClassLoader().getResource(FILENAME).toURI()));

			doc.getDocumentElement().normalize();

			XPath xpath = XPathFactory.newInstance().newXPath();

			Optional<String> value = getNodeValue(doc, xpath,  "/catalog/book/author");
			System.out.println(value.get());

		} catch (ParserConfigurationException | SAXException | IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static Optional<String> getAttributeValue(Node doc, XPath xpath, String path) {

		try {
			Node value = (Node) xpath.compile(path).evaluate(doc,
					XPathConstants.NODE);

			if (value != null && !value.getNodeValue().trim().isEmpty()) {
				return Optional.of(value.getNodeValue().trim());
			}
			return Optional.empty();

		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}
	}

	public static Optional<String> getNodeValue(Node doc, XPath xpath, String path) {

		try {
			Node node = (Node) xpath.compile(path).evaluate(doc,
					XPathConstants.NODE);

			if (node != null) {
				if (node.getFirstChild() != null && node.getFirstChild().getNodeValue() != null
						&& !node.getFirstChild().getNodeValue().trim().isEmpty()) {
					return Optional.of(node.getFirstChild().getNodeValue().trim());
				}
				// return Optional.of("");
			}
			return Optional.empty();

		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}

	}

	public static Optional<NodeList> getNodeChildrenNodes(Node doc, XPath xpath, String path) {

		try {

			NodeList nodeList = (NodeList) xpath.compile(path).evaluate(doc,
					XPathConstants.NODESET);

			if (nodeList != null) {
				return Optional.of(nodeList);
			}
			return Optional.empty();

		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}
	}
}
