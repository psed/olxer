/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.olxer.persistence;

import com.mycompany.olxer.configuration.Config;
import com.mycompany.olxer.configuration.ConfigurationInstance;
import com.mycompany.olxer.configuration.DatabaseConfig;
import com.mycompany.olxer.entity.Ad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class PersistenceHelper {

    private static PersistenceHelper instance;
    private static Connection connection;

    private PersistenceHelper() {
    }

    public static void configuratePersistenceHelper(DatabaseConfig config) {
        try {
            connection = DriverManager.getConnection(config.getConnectionString(), config.getUsername(), config.getPassword());
        } catch (SQLException ex) {
            //TODO: logging
        }
    }

    public static PersistenceHelper getInstance() {
        if (instance == null) {
            instance = new PersistenceHelper();
            configuratePersistenceHelper(ConfigurationInstance.getInstance().getConfig().getDatabaseConfig());
        } else {
            //TODO: logging
        }
        return instance;
    }

    public List<Ad> getAllAds() {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM APP.ADS");
            ResultSet resultSet = prepareStatement.executeQuery();
            List<Ad> ads = new ArrayList<>();
            while (resultSet.next()) {
                ads.add(new Ad(resultSet.getInt("ID"), resultSet.getString("AD_DI")));
            }
            return ads;
        } catch (SQLException ex) {
            return new ArrayList<>();
        }
    }

}
