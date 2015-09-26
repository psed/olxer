/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olxer.persistence;

import olxer.configuration.ConfigurationInstance;
import olxer.configuration.DatabaseConfig;
import olxer.entity.Ad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import olxer.Main;
import olxer.entity.SearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author user
 */
public class PersistenceHelper {

    private static final Logger LOG = LoggerFactory.getLogger(PersistenceHelper.class);
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

    public void addNewAds(List<Ad> ads) {
        for (Ad ad : ads) {
            PreparedStatement prepareStatement;
            try {
                prepareStatement = connection.prepareStatement("INSERT INTO APP.ADS (ID, AD_ID) VALUES ("
                        + ad.getAdId() + ", " + ad.getAdId() + ")");
                boolean execute = prepareStatement.execute();
            } catch (SQLException ex) {
            }
        }
    }

    public List<SearchCriteria> getAllCriterias() {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM APP.CRITERIA");
            ResultSet resultSet = prepareStatement.executeQuery();
            List<SearchCriteria> criteria = new ArrayList<>();
            while (resultSet.next()) {
                criteria.add(new SearchCriteria(resultSet.getInt("ID"), 
                        resultSet.getString("CRITERIA_ULR"), resultSet.getString("EMAIL_TO")));
            }
            return criteria;
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
            return new ArrayList<>();
        }

    }

}
