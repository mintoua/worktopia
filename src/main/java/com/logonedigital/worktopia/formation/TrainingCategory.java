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
public class TrainingCategory extends BaseEntity {

    private String name;
    private String description;
    private String iconName;


    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Training> trainings;

    public TrainingCategory(String name) {
        this.name = name;
    }

}
