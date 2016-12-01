/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;


import java.util.Properties;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;


/**
 *
 * @author huang
 */
@Stateless
@LocalBean
public class EmailSessionBean {

// Définition des configuration 
private int port = 465;
private String host = "smtp.gmail.com";
private String from = "projetjboss@gmail.com";
private boolean auth = true;
private String username = "projetjboss@gmail.com";
private String password = "projet2016";
private Protocol protocol = Protocol.SMTPS;
private boolean debug = true;
    
    public void sendemail(String to, String subject, String body){
        
        // définir les propriétés
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
 switch (protocol) {
    case SMTPS:
        props.put("mail.smtp.ssl.enable", true);
        break;
    case TLS:
        props.put("mail.smtp.starttls.enable", true);
        break;
}
 
 // définir l'authentificateur
 Authenticator authenticator = null;
if (auth) {
    props.put("mail.smtp.auth", true);
    authenticator = new Authenticator() {
        private PasswordAuthentication pa = new PasswordAuthentication(username, password);
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return pa;
        }
    };
}

// Définir une session

Session session = Session.getInstance(props, authenticator);
session.setDebug(debug);

// définir un message à partir de la session avec les propriétés et l'authentificateur
MimeMessage message = new MimeMessage(session);
try {
    // définition de l'adresse qui a envoyé
    message.setFrom(new InternetAddress(from));
    // définition de l'adresse à qui on envoit
    InternetAddress[] address = {new InternetAddress(to)};
    message.setRecipients(Message.RecipientType.TO, address);
    // Définition du sujet
    message.setSubject(subject);
    message.setSentDate(new Date());
    // Défintion du message
    message.setText(body);
    Transport.send(message);
} catch (MessagingException ex) {
    ex.printStackTrace();
}
        
    }
}
