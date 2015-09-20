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
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
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
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailConfig.getUsername(), emailConfig.getPassword());
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("paulsedeen@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("paulsedeen@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler, No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties createEmailClientProperties(EmailConfig emailConfig) {
        Properties props = new Properties();
        props.put(SMTP_AUTH.toString(), emailConfig.isSmtpAuth());
        props.put(SMTP_STARTTLS_ENABLE.toString(), emailConfig.isStarttlsEnable());
        props.put(SMTP_HOST.toString(), emailConfig.getSmtpHost());
        props.put(SMTP_PORT.toString(), emailConfig.getPort());
        props.put("mail.smtp.user", "paulsedeen@gmail.com");
        props.put("mail.smtp.password", "Kingnothing1");
        return props;
    }

}
