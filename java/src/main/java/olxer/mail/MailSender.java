/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olxer.mail;

import olxer.configuration.ConfigurationInstance;
import olxer.configuration.EmailConfig;
import olxer.entity.Ad;
import static olxer.mail.EmailClientProperties.SMTP_AUTH;
import static olxer.mail.EmailClientProperties.SMTP_HOST;
import static olxer.mail.EmailClientProperties.SMTP_PORT;
import static olxer.mail.EmailClientProperties.SMTP_STARTTLS_ENABLE;
import static olxer.mail.EmailClientProperties.SMTP_SSL_SOCKET_FACTORY;
import com.sun.mail.util.MailSSLSocketFactory;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;

/**
 *
 * @author user
 */
public class MailSender {
    
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MailSender.class);

    public static void sendNewAdsSpottedEmail(List<Ad> ads) {
        final EmailConfig emailConfig = ConfigurationInstance.getInstance().getConfig().getEmailConfig();
        Properties props = createEmailClientProperties(emailConfig);
        GmailAuthenticator gmailAuthenticator = new GmailAuthenticator(emailConfig.getUsername(), emailConfig.getPassword());
        Session session = Session.getInstance(props, gmailAuthenticator);

        try {

            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("paulsedeen@gmail.com"));
            message.setSubject("[OLX] Some new stuff found.");
            message.setText(createMessageBody(ads));

            LOG.info("Sending new ads urls via email");
            //Transport.send(message);

        } catch (MessagingException e) {
            LOG.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private static Properties createEmailClientProperties(EmailConfig emailConfig) {
        
        try {
            Properties props = new Properties();
            props.put(SMTP_AUTH.toString(), emailConfig.isSmtpAuth());
            props.put(SMTP_STARTTLS_ENABLE.toString(), emailConfig.isStarttlsEnable());
            props.put(SMTP_HOST.toString(), emailConfig.getSmtpHost());
            props.put(SMTP_PORT.toString(), emailConfig.getPort());
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put(SMTP_SSL_SOCKET_FACTORY, sf);
            return props;
        } catch (GeneralSecurityException ex) {
            LOG.error(ex.getLocalizedMessage());
            return null;
        }
    }

    private static String createMessageBody(List<Ad> ads) {
        StringBuilder messageBody = new StringBuilder();
        for (Ad ad : ads) {
            messageBody.append(ad.getUrl()).append("\n");
        }
        return messageBody.toString();
    }

}
