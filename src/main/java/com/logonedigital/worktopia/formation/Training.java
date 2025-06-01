package com.logonedigital.worktopia.formation;

import com.logonedigital.worktopia.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

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
    private Integer durationInDay;
    private Integer durationInHours;
    private String level;
    private Date dateDebut;
    private String location;
    private String periode;
    private Integer numberOfPlaces;
    private String fichierFacultatif;
    private Integer coutFormation;
    private  String budgetImpute;
    private boolean inscriptionOuverte;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private TrainingCategory category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;
}