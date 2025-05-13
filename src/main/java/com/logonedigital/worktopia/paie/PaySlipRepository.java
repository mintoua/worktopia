package com.logonedigital.worktopia.paie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaySlipRepository extends JpaRepository<PaySlip, Long> {
}
