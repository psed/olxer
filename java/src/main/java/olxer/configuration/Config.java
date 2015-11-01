/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olxer.configuration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@XmlRootElement
public class Config {

    private DatabaseConfig databaseConfig;
    private EmailConfig emailConfig;

    public Config() {
    }

    public Config(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    public DatabaseConfig getDatabaseConfig() {
        return databaseConfig;
    }

    @XmlElement
    public void setDatabaseConfig(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

        public EmailConfig getEmailConfig() {
        return emailConfig;
    }

    @XmlElement
    public void setEmailConfig(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }

}
