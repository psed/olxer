/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olxer.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import olxer.entity.Ad;
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

    private static final String CLASS = "class";
    private static final String CLASS_ATTRIBUTE_VALUE = "thumb vtop inlblk rel tdnone linkWithHash scale4 detailsLink";
    private static final String HREF = "href";
    private static final String NOTHING_ROUND_ERROR_TEXT = "Проверьте правильность написания или введите другие параметры поиска";

    public static Document getPage(String pageUrl) {
        try {
            Document document = Jsoup.connect(pageUrl).get();
            return document;
        } catch (IOException ex) {
            LOG.error(ex.getMessage());
            return new Document(pageUrl);
        }
    }

    public static List<Ad> getAllAdsUrlsForPage(String pageUrl) {
        List<Ad> result = new ArrayList();
        int pageIndex = 1;
        int maxPageNumber = getMaxPageNumber(pageUrl);
        do {

            String currentPageUrl;
            if (pageIndex == 1) {
                currentPageUrl = pageUrl;
            } else {
                currentPageUrl = pageUrl + "?page=" + pageIndex;
            }

            result.addAll(getAllAdsOnPage(currentPageUrl));

            pageIndex++;
        } while (pageIndex == maxPageNumber);

        return result;
    }

    private static int getMaxPageNumber(String pageUrlt) {
        List<String> result = new ArrayList();
        Document page = getPage(pageUrlt);
        Elements pagesUrlsElements = page.getElementsByAttributeValueContaining(HREF, "page=");
        for (Element pageUrl : pagesUrlsElements) {
            String linkString = pageUrl.attr(HREF);
            result.add(linkString.substring(linkString.indexOf("=") + 1, linkString.length()));
        }
        if (result.size() > 0) {
            result.sort(new AscendingSortingComparator());
            return Integer.parseInt(result.get(0));
        }
        return 0;
    }

    private static List<Ad> getAllAdsOnPage(String pageUrl) {
        Document page = getPage(pageUrl);
        if (nothingFound(page)) {
            return new ArrayList<>();
        }
        List<Ad> result = new ArrayList<>();
        Elements adsElements = page.getElementsByAttributeValue(SUMMARY_ATTRIBUTE, AD_ATTRIBUTE_TEXT);
        for (Element adElement : adsElements) {
            Elements priceElements = adElement.getElementsContainingOwnText("грн");
            int price = 0;
            if (priceElements.size() > 0) {
                price = getPriceIntValue(priceElements.get(0).text());
            }
            String url = adElement.getElementsByAttributeValueContaining(CLASS, CLASS_ATTRIBUTE_VALUE).get(0).attr(HREF);
            result.add(new Ad(url, -1L, price));
        }
        return result;
    }
    private static final String AD_ATTRIBUTE_TEXT = "Объявление";
    private static final String SUMMARY_ATTRIBUTE = "summary";

    private static int getPriceIntValue(String price) {
        return Integer.parseInt(price
                .replaceAll(RegularExpression.TO_REMOVE_WHITESPACES.toString(), "")
                .replaceAll(RegularExpression.TO_REMOVE_NON_NUMERIC_CHARACTERS.toString(), ""));
    }

    private static boolean nothingFound(Document page) {
        Elements spanElements = page.getElementsByClass(MARKER);
        if (spanElements.size() == 1) {
            if (spanElements.get(0).ownText().equals(NOTHING_ROUND_ERROR_TEXT)) {
                return true;
            }
        }
        return false;
    }
    private static final String MARKER = "marker";

}
