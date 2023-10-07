package dev.icaApp.ICABookingApp.service;

import dev.icaApp.ICABookingApp.model.Attendee;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendEmail(String toEmail, String CONTENT, String SUBJECT);

    void sendHTMLEmail(String toEmail, String subject, String htmlContent) throws MessagingException;
}
