/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olxer.threading;

import olxer.entity.Ad;
import olxer.persistence.PersistenceHelper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ChechAdsActiveThread implements Runnable {

    @Override
    public void run() {
        System.out.println("Checking ads active");
        List<Ad> allAds = PersistenceHelper.getInstance().getAllAds();
        for (Ad allAd : allAds) {
            
        }
    }

}
