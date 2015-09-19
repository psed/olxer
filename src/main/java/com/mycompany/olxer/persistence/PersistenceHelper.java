/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.olxer.persistence;

import com.mycompany.olxer.configuration.Config;
import com.mycompany.olxer.configuration.DatabaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class PersistenceHelper {

    public static Connection init(Config config) throws SQLException {

        DatabaseConfig databaseConfig = config.getDatabaseConfig();

        return DriverManager.getConnection(databaseConfig.getConnectionString(),
                databaseConfig.getUsername(), databaseConfig.getPassword());

    }

}
