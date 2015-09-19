/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.olxer.threading;

import com.mycompany.olxer.configuration.Config;
import com.mycompany.olxer.configuration.ConfigurationInstance;
import com.mycompany.olxer.entity.Ad;
import com.mycompany.olxer.persistence.PersistenceHelper;
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
        do {
            System.out.println("Checking ads active");
            List<Ad> allAds = PersistenceHelper.getInstance().getAllAds();
            for (Ad allAd : allAds) {
                System.out.println("checking");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ChechAdsActiveThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

}
