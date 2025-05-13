package com.logonedigital.worktopia.employe;

import com.logonedigital.worktopia.email.EmailService;
import com.logonedigital.worktopia.user.Role;
import com.logonedigital.worktopia.user.User;
import com.logonedigital.worktopia.user.UserService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeService {

    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final UserService userService;


    public EmployeeService(EmailService emailService, PasswordEncoder passwordEncoder, EmployeeRepository employeeRepository, UserService userService) {
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
        this.userService = userService;
    }

    @Transactional
    @Async
    public void save(EmployeeRequest request) throws MessagingException {

        var password  = UUID.randomUUID().toString().substring(0, 8);
        var relatedUser = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(password))
                .enabled(true)
                .accountLocked(false)
                .role(Role.EMPLOYE)
                .build();

        var savedUser = userService.save(relatedUser);

        var employeeToSave = EmployeeRequest.toEmployee(request);
        employeeToSave.setUser(savedUser);
        employeeToSave.setAvailability(Availability.ACTIF);

        var saved = employeeRepository.save(employeeToSave);
        emailService.sendEmailToEmployee(saved.getEmail(),password);
    }


}
