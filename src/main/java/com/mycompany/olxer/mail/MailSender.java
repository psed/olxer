/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.olxer.mail;

import com.mycompany.olxer.configuration.ConfigurationInstance;
import com.mycompany.olxer.configuration.EmailConfig;
import com.mycompany.olxer.entity.Ad;
import static com.mycompany.olxer.mail.EmailClientProperties.SMTP_AUTH;
import static com.mycompany.olxer.mail.EmailClientProperties.SMTP_HOST;
import static com.mycompany.olxer.mail.EmailClientProperties.SMTP_PORT;
import static com.mycompany.olxer.mail.EmailClientProperties.SMTP_STARTTLS_ENABLE;
import com.sun.mail.util.MailSSLSocketFactory;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author user
 */
public class MailSender {

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

            Transport.send(message);

        } catch (MessagingException e) {
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
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.socketFactory", sf);
            return props;
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(MailSender.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private static String createMessageBody(List<Ad> ads) {
        StringBuilder messageBody = new StringBuilder();
        for (Ad ad : ads) {
            messageBody.append(ad.getAdId()).append("\n");
        }
        return messageBody.toString();
    }

}
