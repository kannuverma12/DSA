package com.kv.j8;

import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONObject;
import org.json.XML;

public class BeautifyXml {
	
	public static void main(String... args) throws Exception{
		String content = new String(Files.readAllBytes(Paths.get("/Users/karan.verma/Desktop/abc.java")));
        
        JSONObject json = new JSONObject(content);
        String xml = XML.toString(json);
        Files.write(Paths.get("/Users/prabhat.singh/Desktop/abc.java"), xml.getBytes(), StandardOpenOption.CREATE);
        System.out.println(xml);
        
        
//        javax.xml.transform.Transformer transformer = TransformerFactory.newInstance().newTransformer();
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//        //initialize StreamResult with File object to save to file
//        StreamResult result = new StreamResult(new StringWriter());
//        DOMSource source = new DOMSource(xml);
//        transformer.transform(source, result);
//        String xmlString = result.getWriter().toString();
//        System.out.println(xmlString);
	}
	
	
	
	

}
