/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olxer.threading;

import olxer.entity.Ad;
import olxer.persistence.PersistenceHelper;
import java.util.List;

/**
 *
 * @author user
 */
public class ChechAdsActiveThread implements Runnable {

    @Override
    public void run() {
        List<Ad> allAds = PersistenceHelper.getInstance().getAllAds();
        for (Ad allAd : allAds) {
        }
    }

}
