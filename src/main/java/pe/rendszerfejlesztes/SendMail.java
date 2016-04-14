package pe.rendszerfejlesztes;

import javafx.application.Platform;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {

    private SendMail() {
    }

    public static void send(String recipientEmail, String title, String mymessage) {

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("projekt.penzugy", "projektlabor");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("projekt.penzugy"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail));
            message.setSubject(title);
            message.setText(mymessage);

            //Platform.runLater(() -> {
                try {
                    Transport.send(message);
                } catch (Exception e) {
                    System.out.println("Az E-mail kuldes sikertelen volt.");
                }
            //});
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}