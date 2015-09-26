package olxer.threading;

import com.sun.mail.util.MailSSLSocketFactory;
import java.util.List;
import olxer.entity.Ad;
import olxer.entity.SearchCriteria;
import olxer.mail.MailSender;
import olxer.persistence.PersistenceHelper;
import olxer.web.WebPageTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    
    private static final Logger LOG = LoggerFactory.getLogger(NewAdsSearchThread.class);
    
    @Override
    public void run() {
        do {
            List<SearchCriteria> allCriterias = PersistenceHelper.getInstance().getAllCriterias();
            for (SearchCriteria criteria : allCriterias) {
                List<String> links = WebPageTools.getAllLinks(criteria.getCriteriaUrl());
                for (String link : links) {
                    if (adIsNew(link)) {
                        PersistenceHelper.getInstance().addNewAd(link, criteria.getId());
                    }
                }
            }
            
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                LOG.error(ex.getMessage());
            }
        } while (true);
    }
    
    private boolean adIsNew(String adUrl) {
        List<Ad> ads = PersistenceHelper.getInstance().getAllAdsByUrl(adUrl);
        return ads.isEmpty();
    }
    
}
