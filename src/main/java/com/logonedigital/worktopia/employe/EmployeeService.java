package com.logonedigital.worktopia.employe;

import com.logonedigital.worktopia.email.EmailService;
import com.logonedigital.worktopia.exception.RessourceNonExistException;
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
import java.util.Optional;
import java.util.UUID;


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
        employeeToSave.setAvailability("ACTIF"); //statut par défaut

        var saved = employeeRepository.save(employeeToSave);
       // emailService.sendEmailToEmployee(saved.getEmail(),password);
    }

    public List<EmployeeDTO> getAll() {

        List<Employee> trainingList = employeeRepository.findAll();
//        trainingList.forEach(training -> Hibernate.initialize(training.getCategory()));
//        trainingList.forEach(training -> Hibernate.initialize(training.getFormateur()));

        return trainingList
                .stream()
                .map(EmployeeDTO::from)
                .toList();
    }


/*    public List<EmployeeDTO> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();
        return employees
                .stream()
                .map(EmployeeDTO::from)
                .collect(Collectors.toList());
    }*/

    public EmployeeDTO getById(long id) {
        Optional<Employee> employeeOptional = this.employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            return EmployeeDTO.from(employeeOptional.get());
        } else {
            throw new RessourceNonExistException("Training not found with id " + id);
        }
    }

    public EmployeeDTO update(Long id, EmployeeRequest employeeRequest) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            employee.setFirstName(employeeRequest.firstName());
            employee.setLastName(employeeRequest.lastName());
            employee.setPosition(employeeRequest.position());
            employee.setEmail(employeeRequest.email());
            employee.setPhone(employeeRequest.phone());
            employee.setAddress(employeeRequest.address());
            employee.setGender(employeeRequest.gender());
            employee.setDepartment(employeeRequest.department());
            employee.setDateofbirth(employeeRequest.dateofbirth());
            employee.setDateembauche(employeeRequest.dateembauche());
            employee.setManager(employeeRequest.manager());
            employee.setTypecontrat(employeeRequest.typecontrat());
            employee.setSalairebrut(employeeRequest.salairebrut());
            employee.setIban(employeeRequest.iban());
            Employee savedEmployee = employeeRepository.saveAndFlush(employee);
            return EmployeeDTO.from(savedEmployee);
        } else {
            throw new RessourceNonExistException("Employee not found with id " + id);
        }


    }

    public void delete(long id) {
        if (employeeRepository.existsById(id)) {

            employeeRepository.deleteById(id);
        } else {
            throw new RessourceNonExistException("Employee not found with id " + id);
        }
    }
}
