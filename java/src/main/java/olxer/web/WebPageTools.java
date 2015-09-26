/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olxer.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import olxer.util.AscendingSortingComparator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author user
 */
public class WebPageTools {
    
    private static final Logger LOG = LoggerFactory.getLogger(WebPageTools.class);

    private static final String AD_CLASS = "obyavlenie";
    private static final String CLASS = "class";
    private static final String CLASS_ATTRIBUTE_VALUE = "thumb vtop inlblk rel tdnone linkWithHash scale4 detailsLink";
    private static final String REFERENCE_VALUE = "href";
    private static final String A_HREF = "a[href]";

    public static Document getPage(String pageUrl) {
        try {
            Document document = Jsoup.connect(pageUrl).get();
            return document;
        } catch (IOException ex) {
            LOG.error(ex.getMessage());
            return new Document(pageUrl);
        }
    }

    public static List<String> getAllLinks(String pageUrl) {
        List<String> result = new ArrayList();
        int pageIndex = 1;
        int maxPageNumber = getMaxPageNumber(pageUrl);
        do {

            String currentPageUrl;
            if (pageIndex == 1) {
                currentPageUrl = pageUrl;
            } else {
                currentPageUrl = pageUrl + "?page=" + pageIndex;
            }

            result.addAll(getLinksOnPage(currentPageUrl));
            
            pageIndex++;
        } while (pageIndex == maxPageNumber);

        return result;
    }

    private static int getMaxPageNumber(String pageUrlt) {
        List<String> result = new ArrayList();
        Document page = getPage(pageUrlt);
        Elements pagesUrlsElements = page.getElementsByAttributeValueContaining(REFERENCE_VALUE, "page=");
        for (Element pageUrl : pagesUrlsElements) {
            String linkString = pageUrl.attr(REFERENCE_VALUE);
            result.add(linkString.substring(linkString.indexOf("=") + 1, linkString.length()));
        }
        if (result.size() > 0) {
            result.sort(new AscendingSortingComparator());
            return Integer.parseInt(result.get(0));
        }
        return 0;
    }

    private static List<String> getLinksOnPage(String pageUrl) {
        List<String> result = new ArrayList<>();
        Document page = getPage(pageUrl);
        Elements links = page.select(A_HREF);
        for (Element link : links) {
            if (link.toString().contains(AD_CLASS)) {
                Elements elementsByAttributeValueContaining
                        = link.getElementsByAttributeValueContaining(CLASS, CLASS_ATTRIBUTE_VALUE);
                for (Element elementsByAttributeValueContaining1 : elementsByAttributeValueContaining) {
                    String attr = elementsByAttributeValueContaining1.attr(REFERENCE_VALUE);
                    result.add(attr.substring(0, attr.indexOf('#')));
                }
            }
        }
        return result;
    }

}
