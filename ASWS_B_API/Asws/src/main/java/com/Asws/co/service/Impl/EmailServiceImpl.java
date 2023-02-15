package com.Asws.co.service.Impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.Asws.co.service.EmailService;

public class EmailServiceImpl implements EmailService{

    @Override
    public boolean sendEmail(String subject, String message, String to) {

        Boolean f = false;
        
        String host = "smtp.gmail.com";
        String from = "hrkht77@gmail.com";

        Properties properties = System.getProperties();


        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", "hrkht77@gmail.com");
        properties.put("mail.password", "Password@u321");

        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("hrkht77@gmail.com", "Password@u321");
            }
            
        });
                                        
        session.setDebug(true);

        MimeMessage m = new MimeMessage(session);

        try {
            m.setFrom(from);


            m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

            Transport.send(m);

            System.out.println("Send Sucessfully .....");
            f = false;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return f;
        

    }

    public static void main(String[] args) {

        EmailServiceImpl em = new EmailServiceImpl(); 
        System.out.println(em.sendEmail("hhisd","uehuhe","ubatuwah@gmail.com"));
        
    }
                                               
}
