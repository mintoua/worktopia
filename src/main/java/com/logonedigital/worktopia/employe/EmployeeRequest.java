package com.logonedigital.worktopia.employe;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.Date;

@Builder
public record EmployeeRequest(
        @NotEmpty(message = "Firstname is mandatory")
        @NotBlank(message = "Firstname is mandatory")
        String firstName,
        String lastName,
        @NotEmpty(message = "Email is mandatory")
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email not well formatted")
        String email,
        @NotEmpty(message = "Position is mandatory")
        @NotBlank(message = "Position is mandatory")
        String position,
        @NotEmpty(message = "Phone is mandatory")
        @NotBlank(message = "Phone is mandatory")
        String phone,
        @NotEmpty(message = "Address is mandatory")
        @NotBlank(message = "Address is mandatory")
        String address,
        @NotNull(message = "Department is mandatory")
/*        @Pattern(regexp = "^(FINANCE|SOFTWARE|MARKETING)$", message = "Department must be FINANCE, SOFTWARE or MARKETING")*/
        Department department,
          String  dateofbirth,
        String manager,
        Date dateembauche,
        String typecontrat,
         long salairebrut,
        String iban,
        @NotNull(message = "Gender is mandatory")
/*        @Pattern(regexp = "^(MALE|FEMALE)$", message = "Gender must be either MALE or FEMALE")*/
        String gender
) {

    public static EmployeeRequest of(Employee employee) {

        return EmployeeRequest.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .position(employee.getPosition())
                .department(employee.getDepartment())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .address(employee.getAddress())
                .gender(employee.getGender())
                .dateofbirth(employee.getDateofbirth())
                .dateembauche(employee.getDateembauche())
                .manager(employee.getManager())
                .typecontrat(employee.getTypecontrat())
                .salairebrut(employee.getSalairebrut())
                .iban(employee.getIban())
                .build();
    }
    
    public static Employee toEmployee(EmployeeRequest request) {
        return Employee.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .position(request.position())
                .email(request.email())
                .phone(request.phone())
                .address(request.address())
                .gender(request.gender())
                .department(request.department())
                .dateofbirth(request.dateofbirth())
                .dateembauche(request.dateembauche())
                .manager(request.manager())
                .typecontrat(request.typecontrat())
                .salairebrut(request.salairebrut())
                .iban(request.iban())
                .build();
    }
}
