package com.logonedigital.worktopia.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private static final String MULTIPART_MODE = String.valueOf(1);
    private static final String ENCODING;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmailWithPaySlip(String receiverEmail, String paySlipPdf) throws MessagingException {
        MimeMessage message = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("contact@logonedigital.com");
        helper.setTo(receiverEmail);
        helper.setSubject("Pay Slip");
        helper.setText("Hello in attachement your pay slip");
        FileSystemResource file = new FileSystemResource(new File(paySlipPdf));
        helper.addAttachment("paySlip", file);
        this.mailSender.send(message);
    }

    static {
        ENCODING = StandardCharsets.UTF_8.name();
    }

    public void sendEmailToEmployee(String receiverEmail, String password) throws MessagingException {
        MimeMessage message = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("contact@logonedigital.com");
        helper.setTo(receiverEmail);
        helper.setSubject("Credentials Information for Account to Worktopia");
        helper.setText("Hello this is your login info to our RH app Worktopia: \n" +
                "Email: " + receiverEmail + "\nPassword: " + password);
        this.mailSender.send(message);
    }

    public void sendReactivationEmail(String receiverEmail, String subject, String content) throws MessagingException {

        MimeMessage message = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("contact@logonedigital.com");
        helper.setTo(receiverEmail);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }
}
