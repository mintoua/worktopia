package com.logonedigital.worktopia.conge;

import com.logonedigital.worktopia.employe.EmployeeDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestDTO  {

    private Long id;
    private UUID publicId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String motif;
    @Enumerated(EnumType.STRING)
    private LeaveStatus status = LeaveStatus.AWAITING; // EN_ATTENTE(AWAITING), APPROUVE(APPROVED), REJETE(REJECTED)
    private EmployeeDTO employee;

    public static LeaveRequest toEntity(LeaveRequestDTO dto) {
        return LeaveRequest.builder()
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .motif(dto.getMotif())
                .status(dto.getStatus())
                .employee(EmployeeDTO.toEmployee(dto.getEmployee()))
                .build();
    }

    public static LeaveRequestDTO toDto(LeaveRequest request) {
        return LeaveRequestDTO.builder()
                .id(request.getDbId())
                .publicId(request.getPublicId())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .motif(request.getMotif())
                .status(request.getStatus())
                .build();
    }

}