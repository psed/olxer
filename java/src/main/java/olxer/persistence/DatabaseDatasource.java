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
import olxer.entity.Criterias;
import olxer.entity.SearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author user
 */
public class DatabaseDatasource implements DataSource {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseDatasource.class);
    private static DatabaseDatasource instance;
    private static Connection connection;

    private DatabaseDatasource() {
    }

    public static void configuratePersistenceHelper(DatabaseConfig config) {
        try {
            connection = DriverManager.getConnection(config.getConnectionString(), config.getUsername(), config.getPassword());
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
        }
    }

    public static DatabaseDatasource getInstance() {
        if (instance == null) {
            instance = new DatabaseDatasource();
            configuratePersistenceHelper(ConfigurationInstance.getInstance().getConfig().getDatabaseConfig());
        }
        return instance;
    }

    @Override
    public List<Ad> getAllAds() {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM APP.ADS");
            ResultSet resultSet = prepareStatement.executeQuery();
            List<Ad> ads = new ArrayList<>();
            while (resultSet.next()) {
                ads.add(new Ad(resultSet.getString("URL"), resultSet.getString("CRITERIA_ID")));
            }
            return ads;
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Ad> getAllAdsByUrl(String url) {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM APP.ADS WHERE URL = '" + url + "'");
            ResultSet resultSet = prepareStatement.executeQuery();
            List<Ad> ads = new ArrayList<>();
            while (resultSet.next()) {
                ads.add(new Ad(resultSet.getString("URL"), resultSet.getString("CRITERIA_ID")));
            }
            return ads;
        } catch (SQLException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public void addNewAds(List<Ad> ads) {
        for (Ad ad : ads) {
            PreparedStatement prepareStatement;
            try {
                prepareStatement = connection.prepareStatement("INSERT INTO APP.ADS (ID, URL) VALUES ("
                        + ad.getCriteriaId() + ", " + ad.getCriteriaId() + ")");
                boolean execute = prepareStatement.execute();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage());
            }
        }
    }

    @Override
    public List<SearchCriteria> getAllCriterias() {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM APP.CRITERIA");
            ResultSet resultSet = prepareStatement.executeQuery();
            List<SearchCriteria> criteria = new ArrayList<>();
            while (resultSet.next()) {
                criteria.add(new SearchCriteria(resultSet.getInt("ID"),
                        resultSet.getString("CRITERIA_URL"), resultSet.getString("EMAIL_TO")));
            }
            Criterias criterias = new Criterias();
            criterias.setCriterias(criteria);
            return criteria;
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
            return new ArrayList<>();
        }

    }

    @Override
    public void addNewAd(Ad ad) {
        PreparedStatement prepareStatement;
        try {
            prepareStatement = connection.prepareStatement("INSERT INTO APP.ADS (URL, CRITERIA_ID) VALUES ('"
                    + ad.getUrl() + "', " + ad.getCriteriaId() + ")");
            boolean execute = prepareStatement.execute();
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
        }

    }

}
