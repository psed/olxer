/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.olxer.configuration;

import java.io.File;
import javax.xml.bind.*;

/**
 *
 * @author user
 */
public class ConfigurationUtils {

    public static Config initConfigFromFile(String configFilePath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (Config) unmarshaller.unmarshal(new File(configFilePath));
        } catch (javax.xml.bind.JAXBException ex) {
            System.out.println("Config file unmarshalling exception.");
            return new Config();
        }
    }

    public static boolean configurationFileIsNotExists(File file) {
        return !file.exists();
    }

}
