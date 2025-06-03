package com.logonedigital.worktopia.employe;

import com.logonedigital.worktopia.email.EmailService;
import com.logonedigital.worktopia.user.Role;
import com.logonedigital.worktopia.user.User;
import com.logonedigital.worktopia.user.UserService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
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
        log.info("data {}", employeeToSave);

        employeeToSave.setUser(savedUser);
        employeeToSave.setAvailability(Availability.ACTIF); //statut par défaut

        var saved = employeeRepository.save(employeeToSave);
       // emailService.sendEmailToEmployee(saved.getEmail(),password);
    }

    public List<EmployeeDTO> getAll() {

        List<Employee> trainingList = employeeRepository.findAll();
//        trainingList.forEach(training -> Hibernate.initialize(training.getCategory()));
//        trainingList.forEach(training -> Hibernate.initialize(training.getFormateur()));

        return trainingList
                .stream()
                .map(EmployeeDTO::employeeDTO)
                .toList();
    }


    public List<EmployeeDTO> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();
        return employees
                .stream()
                .map(EmployeeDTO::from)
                .collect(Collectors.toList());
    }

}
