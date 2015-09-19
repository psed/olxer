/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.olxer.configuration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@XmlRootElement
public class DatabaseConfig {

    private String connectionString;
    private String username;
    private String password;

    public DatabaseConfig() {
    }

    public DatabaseConfig(String connectionString, String username, String password) {
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
    }

    public String getConnectionString() {
        return connectionString;
    }

    @XmlElement
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getUsername() {
        return username;
    }

    @XmlElement
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    @java.lang.Override
    public String toString() {
        return "DatabaseConfig{" + "connectionString=" + connectionString + ", username=" + username + ", password=" + password + '}';
    }

}
