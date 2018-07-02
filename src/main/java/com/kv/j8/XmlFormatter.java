package com.kv.j8;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Utility Class for formatting XML
 * @author Pankaj
 *
 */
public class XmlFormatter {

	/**
	 *
	 * @param unformattedXml - XML String
	 * @return Properly formatted XML String
	 */
	public String format(String unformattedXml) {
		try {
			Document document = parseXmlFile(unformattedXml);

			OutputFormat format = new OutputFormat(document);
			format.setLineWidth(65);
			format.setIndenting(true);
			format.setIndent(2);
			Writer out = new StringWriter();
			XMLSerializer serializer = new XMLSerializer(out, format);
			serializer.serialize(document);

			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * This function converts String XML to Document object
	 * @param in - XML String
	 * @return Document object
	 */
	private Document parseXmlFile(String in) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(in));
			return db.parse(is);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Takes an XML Document object and makes an XML String. Just a utility
	 * function.
	 *
	 * @param doc - The DOM document
	 * @return the XML String
	 */
	public String makeXMLString(Document doc) {
		String xmlString = "";
		if (doc != null) {
			try {
				TransformerFactory transfac = TransformerFactory.newInstance();
				Transformer trans = transfac.newTransformer();
				trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
				trans.setOutputProperty(OutputKeys.INDENT, "yes");
				StringWriter sw = new StringWriter();
				StreamResult result = new StreamResult(sw);
				DOMSource source = new DOMSource(doc);
				trans.transform(source, result);
				xmlString = sw.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return xmlString;
	}
	public static void main(String args[]) throws Exception{
		XmlFormatter formatter = new XmlFormatter();
//		
		String content = new String(Files.readAllBytes(Paths.get("/Users/karan.verma/Desktop/abc.java")));
		JSONObject json = new JSONObject(content);
        String xml = XML.toString(json);
        Files.write(Paths.get("/Users/karan.verma/Desktop/cdf.java"), xml.getBytes(), StandardOpenOption.CREATE);
        System.out.println(xml);
        
        String xmlcontent = new String(Files.readAllBytes(Paths.get("/Users/karan.verma/Desktop/cdf.java")));
        
        
		//String book = "<?xml version=\"1.0\"?><catalog><book id=\"bk101\"><author>Gambardella, Matthew</author><title>XML Developers Guide</title><genre>Computer</genre><price>44.95</price><publish_date>2000-10-01</publish_date><description>An in-depth look at creating applications with XML.</description></book><book id=\"bk102\"><author>Ralls, Kim</author><title>Midnight Rain</title><genre>Fantasy</genre><price>5.95</price><publish_date>2000-12-16</publish_date><description>A former architect battles corporate zombies, an evil sorceress, and her own childhood to become queen of the world.</description></book></catalog>";
		System.out.println(formatter.format(xmlcontent));
	}
}
