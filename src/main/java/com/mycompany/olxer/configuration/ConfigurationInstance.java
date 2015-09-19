/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.olxer.configuration;

/**
 *
 * @author user
 */
public class ConfigurationInstance {

    private static ConfigurationInstance instance;
    private static Config config;

    private ConfigurationInstance() {
    }

    public static ConfigurationInstance getInstance() {
        if (instance == null) {
            instance = new ConfigurationInstance();
            config = ConfigurationUtils.initConfigFromFile();
        }
        return instance;
    }

    public Config getConfig() {
        return config;
    }

}
