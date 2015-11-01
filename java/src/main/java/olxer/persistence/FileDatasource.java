/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olxer.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import olxer.entity.Ad;
import olxer.entity.Ads;
import olxer.entity.Criterias;
import olxer.entity.SearchCriteria;
import org.slf4j.LoggerFactory;

/**
 *
 * @author user
 */
public class FileDatasource implements DataSource {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(FileDatasource.class);
    private static FileDatasource instance;

    private FileDatasource() {
    }

    public static FileDatasource getInstance() {
        if (instance == null) {
            String userDir = System.getProperty("user.dir");
            checkFileExists(userDir + "/ads.xml");
            checkFileExists(userDir + "/criterias.xml");
            instance = new FileDatasource();
        }
        return instance;
    }

    @Override
    public void addNewAd(Ad ad) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Ads.class, Criterias.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            File file = new File(System.getProperty("user.dir") + "/ads.xml");
            Ads ads = (Ads) unmarshaller.unmarshal(file);
            if (ads.getAds() == null) {
                ads.setAds(new ArrayList<Ad>());
            }
            ads.getAds().add(ad);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(ads, file);
        } catch (JAXBException ex) {

        }
    }

    @Override
    public void addNewAds(List<Ad> adsToAdd) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Ads.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            File file = new File(System.getProperty("user.dir") + "/ads.xml");
            Ads persistentAds = (Ads) unmarshaller.unmarshal(file);
            persistentAds.getAds().addAll(adsToAdd);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(persistentAds, file);
        } catch (JAXBException ex) {
            LOG.error("Error while unmarshalling/unmarshalling");
            LOG.error(ex.getMessage());
        }
    }

    @Override
    public List<Ad> getAllAds() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Ads.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            File file = new File(System.getProperty("user.dir") + "/ads.xml");
            return ((Ads) unmarshaller.unmarshal(file)).getAds();
        } catch (JAXBException ex) {
            LOG.error("Error while unmarshalling");
            LOG.error(ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Ad> getAllAdsByUrl(String url) {
        List<Ad> result = new ArrayList<>();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Ads.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            File file = new File(System.getProperty("user.dir") + "/ads.xml");
            List<Ad> ads = ((Ads) unmarshaller.unmarshal(file)).getAds();
            if (ads == null) {
                return new ArrayList<>();
            }

            for (Ad ad : ads) {
                if (ad.getUrl().equals(url)) {
                    result.add(ad);
                }
            }
        } catch (JAXBException ex) {
            LOG.error("Error while unmarshalling");
            LOG.error(ex.getMessage());
        }
        return result;
    }

    @Override
    public List<SearchCriteria> getAllCriterias() {
        List<SearchCriteria> result = new ArrayList<>();
        try {
            File file = new File(System.getProperty("user.dir") + "/criterias.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Criterias.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Criterias criterias = (Criterias) unmarshaller.unmarshal(file);
            return criterias.getCriterias();
        } catch (JAXBException ex) {
            LOG.error("Error while unmarshalling");
            LOG.error(ex.getMessage());
        }
        return result;
    }

    private static void checkFileExists(String fullPath) {
        File f = new File(fullPath);
        if (!f.exists()) {
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException ex) {
                LOG.error("Error creating new file");
                LOG.error(ex.getMessage());
            }
        }
    }

}
