package com.logonedigital.worktopia.paie;

import com.logonedigital.worktopia.employe.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaySlipRequest {

    private Double baseSalary;
    private Double bonus;
    private Double deduction;
    private Employee employee;
}
