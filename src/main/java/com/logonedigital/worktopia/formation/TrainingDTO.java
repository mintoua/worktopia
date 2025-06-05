package com.logonedigital.worktopia.formation;
import com.logonedigital.worktopia.employe.Employee;
import lombok.Builder;

import java.util.Date;
import java.util.UUID;

@Builder
public record TrainingDTO(
        UUID publicId,
         String title,
         String description,
         Integer numberOfPlaces,
         String date,
         Integer durationInDay,
         Integer durationInHours,
         String level,
         Date dateDebut,
         String location,
         String periode,
         String fichierFacultatif,
         Integer coutFormation,
         String budgetImpute,
         boolean inscriptionOuverte,
         TrainingCategory trainingCategory,
         Formateur formateur
) {


    public static TrainingDTO from(Training training) {

        return TrainingDTO.builder()
                .title(training.getTitle())
                .publicId(training.getPublicId())
                .description(training.getDescription())
                .numberOfPlaces(training.getNumberOfPlaces())
                .date(training.getCreatedDate().toString())
                .durationInDay(training.getDurationInDay())
                .durationInHours(training.getDurationInHours())
                .level(training.getLevel())
                .dateDebut(training.getDateDebut())
                .location(training.getLocation())
                .periode(training.getPeriode())
                .fichierFacultatif(training.getFichierFacultatif())
                .coutFormation(training.getCoutFormation())
                .budgetImpute(training.getBudgetImpute())
                .inscriptionOuverte(training.isInscriptionOuverte())
                .trainingCategory(training.getCategory())
                .formateur(training.getFormateur())
                .build();
    }
}
