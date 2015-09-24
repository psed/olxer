/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olxer.configuration;

import java.io.File;
import javax.xml.bind.*;

/**
 *
 * @author user
 */
public class ConfigurationUtils {
    
    public static Config initConfigFromFile() {
        
        final String configFilePath = System.getProperty("user.dir")  + "/cfg.xml";
        File configurationFile = new File(configFilePath);
        if (configurationFileIsNotExists(configurationFile)) {
            System.exit(0);
        }
        
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (Config) unmarshaller.unmarshal(new File(configFilePath));
        } catch (JAXBException ex) {
            return new Config();
        }
    }
    
    public static boolean configurationFileIsNotExists(File file) {
        return !file.exists();
    }
    
}
