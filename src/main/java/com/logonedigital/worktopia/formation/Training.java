package com.logonedigital.worktopia.formation;

import com.logonedigital.worktopia.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Entity representing a training session with details such as the title,
 * description, number of places, difficulty level, required skills, and duration.
 */
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Training extends BaseEntity {

    private String title;
    private String description;
    private Integer numberOfPlaces;
    private String level;
    private String skills;
    private Integer durationInHours;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private TrainingCategory category;
}