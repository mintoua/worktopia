package com.logonedigital.worktopia.employe;

import lombok.Builder;

@Builder
public record EmployeeDTO(
        String firstName,
        String lastName,
        String email,
        String position
        //etc.
) {

    public static EmployeeDTO from(Employee employee) {

        return EmployeeDTO.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .position(employee.getPosition())
                //etc.
                .build();
    }

    public static Employee toEmployee(EmployeeDTO employeeDTO) {

        return Employee.builder()
                .firstName(employeeDTO.firstName())
                .lastName(employeeDTO.lastName())
                .email(employeeDTO.email())
                .position(employeeDTO.position())
                //etc.
                .build();
    }
}
