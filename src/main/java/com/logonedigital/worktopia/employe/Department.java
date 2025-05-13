package com.logonedigital.worktopia.employe;

import lombok.Getter;

@Getter
public enum Department {
    FINANCE("FINANCE_DEPARTMENT"),
    ITSOLUTIONS("SOFTWARE_DEPARTMENT"),
    MARKETING("MARKETING_DEPARTMENT"),
    DATACONSULTING("DATACONSULTING_DEPARTMENT");

    private static final String ERROR_MESSAGE = "Department code cannot be null or empty";
    private final String departmentCode;

    Department(String departmentCode) {
        validateDepartmentCode(departmentCode);
        this.departmentCode = departmentCode;
    }

    private static void validateDepartmentCode(String departmentCode) {
        assert departmentCode != null && !departmentCode.trim().isEmpty() : ERROR_MESSAGE;
    }
}