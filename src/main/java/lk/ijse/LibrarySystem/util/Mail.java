package lk.ijse.LibrarySystem.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Mail {
    public static void main(String[] args) throws IOException {
        System.out.println("Giya");
        String to = "daniduviraj04@gmail.com"; // to address. It can be any like gmail, hotmail etc.
        final String from = "daniduviraj04@gmail.com"; // from address. As this is using Gmail SMTP.
        final String password = "asyfjjeilcallnoy"; // password for from mail address.
        System.out.println("Giya");
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        System.out.println("Giya");
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        System.out.println("Giya");
        try {
            System.out.println("awa");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Message from Java Simplifying Tech");

            String msg = "This email sent using JavaMailer API from Java Code!!!";
            System.out.println("awa");
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            System.out.println("awa");
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(new File("D:/mods/icons8-library-96.png"));
            multipart.addBodyPart(attachmentBodyPart);
            message.setContent(multipart);
            System.out.println("awa");
            Transport.send(message);
            System.out.println("ssssss");
            System.out.println("Mail successfully sent..");

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}

