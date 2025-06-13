package com.logonedigital.worktopia.employe;
import lombok.Builder;

import java.util.Date;

@Builder
public record EmployeeDTO(
         String firstName,
         String lastName,
         String email,
         String position,
         String phone,
         String address,
         Department department,
         String gender,
          String  dateofbirth,
         String manager,
         Date dateembauche,
         String typecontrat,
         long salairebrut,
          String iban,
         String availability
) {


    public static EmployeeDTO from(Employee employee) {

        return EmployeeDTO.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .position(employee.getPosition())
                .phone(employee.getPhone())
                .address(employee.getAddress())
                .department(employee.getDepartment())
                .gender(employee.getGender())
                .dateofbirth(employee.getDateofbirth())
                .manager(employee.getManager())
                .dateembauche(employee.getDateembauche())
                .typecontrat(employee.getTypecontrat())
                .salairebrut(employee.getSalairebrut())
                .iban(employee.getIban())
                .availability(employee.getAvailability())
                .build();
    }

    public static Employee toEmployee(EmployeeDTO dto) {

        return Employee.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .position(dto.position())
                .phone(dto.phone())
                .address(dto.address())
                .department(dto.department())
                .gender(dto.gender())
                .dateofbirth(dto.dateofbirth())
                .manager(dto.manager())
                .dateembauche(dto.dateembauche())
                .typecontrat(dto.typecontrat())
                .salairebrut(dto.salairebrut())
                .iban(dto.iban())
                .availability(dto.availability())
                .build();
    }
}
