package com.Asws.co.service.Impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailServiceImpl {

    
   
    private  String username = "demod1933@gmail.com";
    private  String password = "pfhuwpaxidihoszv";
    private  Properties props;

    public EmailServiceImpl() {
   
        this.props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public void sendEmail(String to, String subject, String text) throws MessagingException {
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(text);
        Transport.send(message);

    }



    public static void main(String[] args) throws MessagingException {

        EmailServiceImpl emailServiceImpl = new EmailServiceImpl();
        emailServiceImpl.sendEmail("ubatuwah@gmail.com", "password", "testing password");
   
    }
}

                                               

