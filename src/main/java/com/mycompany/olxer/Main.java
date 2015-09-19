package com.mycompany.olxer;

import com.mycompany.olxer.configuration.Config;
import com.mycompany.olxer.configuration.ConfigurationUtils;
import static com.mycompany.olxer.configuration.ConfigurationUtils.configurationFileIsNotExists;
import com.mycompany.olxer.persistence.PersistenceHelper;
import com.mycompany.olxer.web.WebPageTools;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

    private static final String url = "http://krivoyrog.dnp.olx.ua/q-%D1%82%D0%BE%D0%BA%D0%B0%D1%80%D0%BD%D1%8B%D0%B9-%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%BA/?search[private_business]=private&search[filter_float_price%3Afrom]=1500&search[filter_float_price%3Ato]=5000";

    public static void main(String[] args) throws SQLException, JAXBException {

        if (args.length != 1) {
            System.out.println("Please, specify a path to a configuration xml file (1-st. command line argument)");
            System.exit(0);
        }

        final String configFilePath = args[0];
        File configurationFile = new File(configFilePath);
        if (configurationFileIsNotExists(configurationFile)) {
            System.out.println("Can't find configuration file using path " + configFilePath);
            System.exit(0);
        }

        Config appConfig = ConfigurationUtils.initConfigFromFile(args[0]);
        PersistenceHelper.init(appConfig);
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
    }

}
