package com.mycompany.olxer;

import com.mycompany.olxer.entity.Ad;
import com.mycompany.olxer.persistence.PersistenceHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private static final String URL = "http://krivoyrog.dnp.olx.ua/q-%D1%82%D0%BE%D0%BA%D0%B0%D1%80%D0%BD%D1%8B%D0%B9-%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%BA/?search[private_business]=private&search[filter_float_price%3Afrom]=1500&search[filter_float_price%3Ato]=5000";

    public static void main(String[] args) throws SQLException, JAXBException {
        
        List<Ad> ads = new ArrayList<>();
        ads.add(new Ad(3, "123456"));
        PersistenceHelper.getInstance().addNewAds(ads);
        
        /*
         ChechAdsActiveThread checkAdsActive = new ChechAdsActiveThread();
         new Thread(checkAdsActive).start();
         Document page = WebPageTools.getPage(url);
         Elements links = WebPageTools.getLinks(page);
         List<String> adIds = new ArrayList<>();
         for (Element link : links) {
         if (link.attr("class").contains("{id:")) {
         String adId = link.attr("class");
         adIds.add(adId.substring(adId.indexOf('{') + 4, adId.indexOf('}')));
         }
         }
         for (String adId : adIds) {
         System.out.println(adId);
         }
         */
    }

}
