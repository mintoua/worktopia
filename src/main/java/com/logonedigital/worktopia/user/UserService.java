package com.logonedigital.worktopia.user;

import com.logonedigital.worktopia.email.EmailService;
import com.logonedigital.worktopia.user.password.VerificationRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    @Async
    public void resetPassword(String email, String newPassword) {
        User user =  userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setEnabled(false);
        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(15));
        userRepository.save(user);
        sendReactivationEmail(user);
    }

    private void sendReactivationEmail(User user) {
        String subject = "Reactivate your account, your password has been changed";
        String verificationCode = user.getVerificationCode() ;
        String htmlContent = """
                            <div style="font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto;">
                                <h2 style="color: #2c3e50;">Please Reactivate your account</h2>
                                <p style="color: #34495e; line-height: 1.6;">Your password has been changed, please reactivate your account using this code and link:</p>
                                <div style="background-color: #f8f9fa; padding: 20px; border-radius: 5px; text-align: center; margin: 20px 0;">
                                    <span style="font-size: 24px; font-weight: bold; color: #2c3e50; letter-spacing: 2px;">%s</span>
                                </div>
                                <p style="color: #7f8c8d; font-size: 14px;">This code will expire in 15 minutes.</p>
                            </div>
                            """.formatted(verificationCode);

        try {
            emailService.sendReactivationEmail(user.getEmail(), subject, htmlContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }

    public void reactivateAccount(VerificationRequest request) {
        User user =  userRepository.findByEmail(request.email())
                .orElseThrow(()->new RuntimeException("User not found"));
        if(user.getVerificationCodeExpiresAt().isBefore(LocalDateTime.now()))
        {
            throw new RuntimeException("Verification code has expired");
        }
        if(!user.getVerificationCode().equals(request.code()))
        {
            throw new RuntimeException("Verification code is incorrect");
        }
        user.setEnabled(true);
        user.setVerificationCode(null);
        user.setVerificationCodeExpiresAt(null);
        userRepository.save(user);
    }
}
