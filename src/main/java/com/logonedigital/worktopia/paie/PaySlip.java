package com.logonedigital.worktopia.paie;

import com.logonedigital.worktopia.common.BaseEntity;
import com.logonedigital.worktopia.employe.Employee;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PaySlip extends BaseEntity {

    private Double baseSalary;
    private Double bonus;
    private Double deduction;
    private String pdfLink;
    private LocalDateTime generatedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

}