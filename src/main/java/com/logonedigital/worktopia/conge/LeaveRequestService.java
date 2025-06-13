package com.logonedigital.worktopia.conge;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveRequestService {

    private final LeaveRequestRepository repository;

    public LeaveRequestService(LeaveRequestRepository leaveRequestRepository) {
        this.repository = leaveRequestRepository;
    }

    public LeaveRequestDTO save(LeaveRequestDTO request) {
        request.setStatus(LeaveStatus.AWAITING);
        LeaveRequest savedLeavingRequest = repository.save((LeaveRequestDTO.toEntity(request)));
        return LeaveRequestDTO.toDto(savedLeavingRequest);
    }

    public List<LeaveRequestDTO> getAllLeaveRequests() {
        return repository.findAll().stream().map(LeaveRequestDTO::toDto).collect(Collectors.toList());
    }

    public LeaveRequestDTO getLeaveRequestByEmployee(Long employeeId) {
        return repository.findByEmployeeDbId(employeeId)
                .stream()
                .map(LeaveRequestDTO::toDto)
                .findFirst()
                .orElse(null);
    }

    public LeaveRequestDTO approve(Long leaveRequestId) {
        LeaveRequest leaveRequest = repository.findById(leaveRequestId).orElseThrow(()-> new RuntimeException("Leave Request Not Found"));
        assert leaveRequest != null;
        leaveRequest.setStatus(LeaveStatus.APPROVED);
        repository.save(leaveRequest);
        return LeaveRequestDTO.toDto(leaveRequest);
    }

    public LeaveRequestDTO reject(Long leaveRequestId) {
        LeaveRequest leaveRequest = repository.findById(leaveRequestId).orElseThrow(()->new RuntimeException("Leave Request Not Found"));
        assert leaveRequest != null;
        leaveRequest.setStatus(LeaveStatus.REJECTED);
        repository.save(leaveRequest);
        return LeaveRequestDTO.toDto(leaveRequest);
    }


}
