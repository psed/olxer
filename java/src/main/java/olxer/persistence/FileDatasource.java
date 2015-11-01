/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olxer.persistence;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import olxer.entity.Ad;
import olxer.entity.Ads;
import olxer.entity.Criterias;
import olxer.entity.SearchCriteria;

/**
 *
 * @author user
 */
public class FileDatasource implements DataSource {

    private static FileDatasource instance;

    private FileDatasource() {
    }

    public static FileDatasource getInstance() {
        if (instance == null) {
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
            ads.getAds().add(ad);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(ads, file);
            
        } catch (JAXBException ex) {
            Logger.getLogger(FileDatasource.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void addNewAds(List<Ad> ads) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ad> getAllAds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ad> getAllAdsByUrl(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SearchCriteria> getAllCriterias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
