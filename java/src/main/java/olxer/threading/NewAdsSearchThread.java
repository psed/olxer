package olxer.threading;

import java.util.List;
import olxer.entity.SearchCriteria;
import olxer.persistence.PersistenceHelper;
import olxer.web.WebPageTools;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class NewAdsSearchThread implements Runnable {

    @Override
    public void run() {

        List<SearchCriteria> allCriterias = PersistenceHelper.getInstance().getAllCriterias();
        for (SearchCriteria criteria : allCriterias) {
            List<String> linksOnPage = WebPageTools.getLinksOnPage(criteria.getCriteriaUrl());
        }

    }

}
