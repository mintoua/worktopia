package com.logonedigital.worktopia.conge;

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
@Table(name = "conge")
public class LeaveRequest extends BaseEntity {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @Column(columnDefinition = "text")
    private String motif;
    @Enumerated(EnumType.STRING)
    private LeaveStatus status = LeaveStatus.AWAITING; // EN_ATTENTE(AWAITING), APPROUVE(APPROVED), REJETE(REJECTED)
    @ManyToOne
    private Employee employee;


}