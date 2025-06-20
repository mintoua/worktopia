package com.logonedigital.worktopia.conge;

import com.logonedigital.worktopia.employe.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeDbId(Long employeeDbId);


}
