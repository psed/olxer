/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olxer.persistence;

import java.util.List;
import olxer.entity.Ad;
import olxer.entity.SearchCriteria;

/**
 *
 * @author user
 */
public interface DataSource {

    void addNewAd(Ad ad);

    void addNewAds(List<Ad> ads);

    List<Ad> getAllAds();

    List<Ad> getAllAdsByUrl(String url);

    List<SearchCriteria> getAllCriterias();
    
}
