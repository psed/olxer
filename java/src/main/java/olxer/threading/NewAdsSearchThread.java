package olxer.threading;

import java.util.ArrayList;
import java.util.List;
import javax.activation.FileDataSource;
import olxer.entity.Ad;
import olxer.entity.SearchCriteria;
import olxer.mail.MailSender;
import olxer.persistence.DataSource;
import olxer.persistence.DatabaseDatasource;
import olxer.persistence.FileDatasource;
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
    private final DataSource dataSource = FileDatasource.getInstance();

    @Override
    public void run() {
        int runNumber = 0;
        do {
            runNumber++;
            LOG.info("Run# " + runNumber);
            LOG.info("Checking criterias");
            List<SearchCriteria> allCriterias = dataSource.getAllCriterias();
            List<Ad> adsToSend = new ArrayList<>();
            for (SearchCriteria criteria : allCriterias) {
                List<String> links = WebPageTools.getAllLinks(criteria.getCriteriaUrl());
                LOG.info("Checking criteria #" + criteria.getId());
                boolean newAdsWereNotFound = true;
                for (String link : links) {
                    if (adIsNew(link)) {
                        Ad Ad = new Ad(link, Long.toString(criteria.getId()));
                        addNewAd(Ad);
                        adsToSend.add(Ad);
                        newAdsWereNotFound = false;
                    }
                }
                if (newAdsWereNotFound) {
                    LOG.info("No new ads");
                }
                if (!adsToSend.isEmpty()) {
                    sendEmailSpottedMail(adsToSend);
                }

            }

            try {
                LOG.info("Sleeping thread");
                Thread.sleep(1800000);
                LOG.info("Wakeup thread");
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
                LOG.error(ex.getMessage());
            }
        } while (true);
    }

    private boolean adIsNew(String adUrl) {
        List<Ad> ads = dataSource.getAllAdsByUrl(adUrl);
        return ads.isEmpty();
    }

    private void addNewAd(Ad ad) {
        dataSource.addNewAd(ad);
    }

    private void sendEmailSpottedMail(List<Ad> ads) {
        LOG.info("Sending new ads urls via email");
        MailSender.sendNewAdsSpottedEmail(ads);
        LOG.info("Email sent.");
    }

}
