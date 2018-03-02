/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasaxparser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author enoch
 */
public class XMLNode {
    public String name;
    public String content;
    public HashMap<String, String> attributes;
    public ArrayList<XMLNode> children;
    public XMLNode() {
    this.attributes = new HashMap();
    this.children = new ArrayList();
    }
}
