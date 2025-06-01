package com.logonedigital.worktopia.formation;

import java.util.Date;

public record TrainingRequest(
        String title,
        String description,
        Integer durationInDay,
        Integer durationInHours,
        String level,
        Date dateDebut,
        String location,
        String periode,
        Integer numberOfPlaces,
        String fichierFacultatif,
        Integer coutFormation,
        String budgetImpute,
        boolean inscriptionOuverte,
         TrainingCategory trainingCategory,
         Formateur formateur

) {
    public static Training of(TrainingRequest request) {
        Training training = new Training();
        training.setTitle(request.title());
        training.setDescription(request.description());
        training.setDurationInDay(request.durationInDay());
        training.setDurationInHours(request.durationInHours());
        training.setDateDebut(request.dateDebut());
        training.setLocation(request.location());
        training.setNumberOfPlaces(request.numberOfPlaces());
        training.setFichierFacultatif(request.fichierFacultatif());
        training.setCoutFormation(request.coutFormation());
        training.setBudgetImpute(request.budgetImpute());
        training.setInscriptionOuverte(request.inscriptionOuverte());
        training.setCategory(request.trainingCategory());
        training.setFormateur(request.formateur());

/*        Training training1 =  Training.builder()
                .title(request.title())
                .description(request.description())
                .numberOfPlaces(request.numberOfPlaces())
                .level(request.level())
                .skills(request.skills())
                .durationInHours(request.durationInHours())
                .category(request.category())
                .build();*/
        return training;
    }


    public void withCategory(TrainingCategory trainingCategory, Formateur formateur) {
        new TrainingRequest(
                title,
                description,
                durationInDay,
                durationInHours,
                level,
                dateDebut,
                location,
                periode,
                numberOfPlaces,
                fichierFacultatif,
                coutFormation,
                budgetImpute,
                inscriptionOuverte,
                trainingCategory,
                formateur
        );
    }

}
