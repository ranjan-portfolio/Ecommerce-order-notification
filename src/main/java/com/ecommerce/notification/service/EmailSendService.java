package com.ecommerce.notification.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSendService {
    
    private final JavaMailSender javaMailSender;

    public EmailSendService(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    public String sendEmail(String to, String from, String subject,String mesage){
        MimeMessage message=javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper messageHelper=new MimeMessageHelper(message);
            messageHelper.setTo(to);
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);
            messageHelper.setText(mesage, false);
            javaMailSender.send(message);
            return "Email sent successfully";
        }catch(Exception ex){
            return "Error encountered while sending message";
        }
    }
}
