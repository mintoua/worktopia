package com.logonedigital.worktopia.formation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    boolean existsByTitleAndLevel(String title, String level);
}
