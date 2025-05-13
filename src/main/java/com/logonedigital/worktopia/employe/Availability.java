package com.logonedigital.worktopia.employe;

import lombok.Getter;

@Getter
public enum Availability {
    ACTIF("STATUT_ACTIF"),
    VOCATION("STATUT_VOCATION"),
    HOLIDAY("STATUT_HOLIDAY");

    private static final String ERROR_MESSAGE = "Disponibilité cannot be null or empty";
    private final String disponible;

    Availability(String disponible) {
        validateDisponible(disponible);
        this.disponible = disponible;
    }

    private static void validateDisponible(String disponible) {
        if (disponible == null || disponible.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }
}