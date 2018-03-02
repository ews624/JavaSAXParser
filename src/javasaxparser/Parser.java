/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasaxparser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author enoch
 */
public class Parser {
    private static XMLNode root;
    private static Stack<XMLNode> stack;
    private static XMLNode current;
    
    private static String input;
    
    
    public static XMLNode parse(File file) throws Exception {
        try {
            input = "";
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
             DefaultHandler handler = new DefaultHandler() {
                 @Override
                 public void startDocument() throws SAXException {
                     root = null;
                     stack = new Stack<>();
                }
                 
                 @Override
                 public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                     XMLNode node = new XMLNode();
                     
                     node.name = qName;
                     System.out.println(qName);
                     input += node.name + "\n";
                     
                     node.attributes = new HashMap();
                     int size = attributes.getLength();
                     
                     for (int i = 0; i < size; i++) {
                         node.attributes.put(attributes.getQName(i), attributes.getValue(i));
                     }
                     stack.push(node);
                     
                     if (current != null) {
                         if (current.children == null) {
                             current.children = new ArrayList();
                         }
                             current.children.add(node);
                         
                     }
                     current = node; 
                 } // end start element
                 
                 @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    current.content = new String(ch, start, length);
                    
                    input += current.content + "\n";
                }
                 @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    XMLNode popped = stack.pop();
                    //if (popped != null) {
                        if (stack.empty()) {
                            root = popped;
                            current = null;
                        }
                        else {
                            current = stack.peek();
                        }
                  //  }
                }
             };
             
             saxParser.parse(file.getAbsoluteFile(), handler);
             
        } catch (Exception e) {
            throw e;
        }
        
        return root;
    }
    public static String returnInput(){
        return Parser.input;
    }
    
}
