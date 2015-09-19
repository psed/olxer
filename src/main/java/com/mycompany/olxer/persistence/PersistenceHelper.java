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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class PersistenceHelper {

    public static void init(Config config) throws SQLException {

        DatabaseConfig databaseConfig = config.getDatabaseConfig();

        Connection connection = DriverManager.getConnection(databaseConfig.getConnectionString(),
                databaseConfig.getUsername(), databaseConfig.getPassword());
        Statement createStatement = connection.createStatement();
        String query = "select * from APP.CUSTOMER";
        ResultSet executeQuery = createStatement.executeQuery(query);
        while (executeQuery.next()) {
            System.out.println(executeQuery.getString("NAME"));
        }
    }

}
