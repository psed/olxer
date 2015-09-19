/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.olxer.web;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author user
 */
public class WebPageTools {
    
    public static Document getPage(String pageUrl) {
        try {
            Document document = Jsoup.connect(pageUrl).get();
            return document;
        } catch (IOException ex) {
            System.out.println("Exception while retrieving page: " + ex.getMessage());
            return null;
        }
    }
    
    public static Elements getLinks(Document document) {
        return document.select("a[href]");
    }


}
