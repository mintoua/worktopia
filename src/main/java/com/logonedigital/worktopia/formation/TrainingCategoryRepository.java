package com.logonedigital.worktopia.formation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainingCategoryRepository extends JpaRepository<TrainingCategory, Long> {
    TrainingCategory findByName(String name);
}
