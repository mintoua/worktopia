package com.logonedigital.worktopia.employe;
import com.logonedigital.worktopia.formation.Formateur;
import com.logonedigital.worktopia.formation.Training;
import com.logonedigital.worktopia.formation.TrainingCategory;
import lombok.Builder;

import java.util.Date;
import java.util.UUID;

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


    public static EmployeeDTO employeeDTO(Employee employee) {

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
}
