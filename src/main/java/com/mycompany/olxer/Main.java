package com.mycompany.olxer;

import com.mycompany.olxer.configuration.Config;
import com.mycompany.olxer.configuration.ConfigurationUtils;
import java.io.File;
import java.sql.SQLException;

import static com.mycompany.olxer.configuration.ConfigurationUtils.*;
import com.mycompany.olxer.persistence.PersistenceHelper;

import javax.xml.bind.JAXBException;

public class Main {

    public static void main(String[] args) throws SQLException, JAXBException {

        if (args.length != 1) {
            System.out.println("Please, specify a path to a configuration xml file (1-st. command line argument)");
            System.exit(0);
        }

        final String configFilePath = args[0];
        File configurationFile = new File(configFilePath);
        if (configurationFileIsNotExists(configurationFile)) {
            System.out.println("Can't find configuration file using path " + configFilePath);
            System.exit(0);
        }

        Config appConfig = ConfigurationUtils.initConfigFromFile(args[0]);
        PersistenceHelper.init(appConfig);
        
    }

}
