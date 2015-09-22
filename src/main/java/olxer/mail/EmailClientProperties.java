/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olxer.mail;

/**
 *
 * @author user
 */
public enum EmailClientProperties {

    SMTP_AUTH("mail.smtp.auth"),
    SMTP_STARTTLS_ENABLE("mail.smtp.starttls.enable"),
    SMTP_HOST("mail.smtp.host"),
    SMTP_PORT("mail.smtp.port"),
    SMTP_SSL_SOCKET_FACTORY("mail.smtp.ssl.socketFactory");

    private final String text;

    private EmailClientProperties(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
