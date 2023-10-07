package dev.icaApp.ICABookingApp.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService{

    private JavaMailSender javaMailSender;
    public static final String SUBJECT = "Test Email";
    public static final String CONTENT = "Welcome to Abuja_Event \n Log in to view your ticket";

    public void sendEmail(String toEmail, String CONTENT, String SUBJECT) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(SUBJECT);
        mailMessage.setText(CONTENT);
        javaMailSender.send(mailMessage);
    }
    public void sendHTMLEmail(String toEmail, String subject, String htmlContent) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(htmlContent,true);
        javaMailSender.send(mimeMessage);
    }
}


