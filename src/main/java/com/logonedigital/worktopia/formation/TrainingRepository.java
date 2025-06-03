package com.logonedigital.worktopia.formation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrainingRepository extends JpaRepository<Training, UUID> {
    boolean existsByTitleAndLevel(String title, String level);
}
