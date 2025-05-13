package com.logonedigital.worktopia.employe;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("Homme"),
    FEMALE("Femme");

    private final String description;

    Gender(String description) {
        this.description = description;
    }
}