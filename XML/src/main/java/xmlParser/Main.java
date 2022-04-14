package xmlParser;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Stack;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class Main {

	private static final String FILENAME = "example.xml";
//	private static final String FILENAME = "/Users/gek/Dev/Git/Java/WRAP.XML";

	
	public static void main(String[] args) {
		
	      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	      
	      try {
	    	  
	    	  dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

	          DocumentBuilder db = dbf.newDocumentBuilder();

	          Document doc = db.parse(new File(Main.class.getClassLoader().getResource(FILENAME).toURI()));
	          
	          doc.getDocumentElement().normalize();
	          
//	          System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());

	          Stack<NodeWithLevel> stack = new Stack<>();
	          
	          stack.add(new NodeWithLevel(doc.getDocumentElement(), 0));
	          
	          while(!stack.isEmpty()) {
	        	  
	        	  NodeWithLevel node = stack.pop();
	        	  
	        	  String indent = String.join("", Collections.nCopies(node.getLevel(), "\t"));
	        	  
	        	  String attributes = null;
	        	  if(node.getNode().getAttributes().getLength() > 0) {
	        		  attributes = " | ATTRIBUTES -> ";
		        	  for(int i = 0 ; i < node.getNode().getAttributes().getLength(); i++) {
		        		  attributes = attributes + " " + node.getNode().getAttributes().item(i).getNodeName() + " - " + node.getNode().getAttributes().item(i).getNodeValue() + " | ";
	 	        	  }
	        	  } else {
	        		  attributes = "";
	        	  }
	        	
	        	  
		          System.out.println(indent + node.getNode().getNodeName() + attributes);
		          
		          for(int i = 0 ; i < node.getNode().getChildNodes().getLength(); i++) {
		        	  if(Integer.valueOf(node.getNode().getChildNodes().item(i).getNodeType()) != 3) {
		        	  	stack.add(new NodeWithLevel(node.getNode().getChildNodes().item(i), node.getLevel()+1));
		        	  } else {
		        		  if(!node.getNode().getChildNodes().item(i).getNodeValue().trim().isEmpty()) {
			        		  System.out.println(indent + "VALUE -> " + node.getNode().getChildNodes().item(i).getNodeValue());
		        		  }
		        	  }
		          }
		         
	          }
	          
	      } catch (ParserConfigurationException | SAXException | IOException | URISyntaxException e) {
	          e.printStackTrace();
	      }
	}
	
	public static class NodeWithLevel {
		
		private Node node;
		private int level;
		
		public NodeWithLevel(Node node, int level) {
			this.node = node;
			this.level = level;
		}
		
		public Node getNode() {
			return node;
		}
		public int getLevel() {
			return level;
		}
		
	}
}
