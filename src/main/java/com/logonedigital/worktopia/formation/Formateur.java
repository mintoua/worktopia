package com.logonedigital.worktopia.formation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logonedigital.worktopia.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Formateur extends BaseEntity {

    private String nomFormateur;
    private String emailFormateur;
    private Integer telephoneFormateur;
    private String typeFormateur;

    @JsonIgnore
    @OneToMany(mappedBy = "formateur")
    private List<Training> trainings;

    public Formateur(String nomFormateur) {
        this.nomFormateur = nomFormateur;
    }

    public Formateur(String nomFormateur, Integer telephoneFormateur, String emailFormateur, String typeFormateur) {
    }
}
