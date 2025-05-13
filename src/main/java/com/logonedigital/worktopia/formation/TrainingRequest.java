package com.logonedigital.worktopia.formation;

public record TrainingRequest(
         String title,
         String description,
         Integer numberOfPlaces,
         String level,
         String skills,
         Integer durationInHours,
         TrainingCategory trainingCategory
) {
    public static Training of(TrainingRequest request) {
        Training training = new Training();
        training.setTitle(request.title());
        training.setDescription(request.description());
        training.setNumberOfPlaces(request.numberOfPlaces());
        training.setLevel(request.level());
        training.setSkills(request.skills());
        training.setDurationInHours(request.durationInHours());
        training.setCategory(request.trainingCategory());

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


    public void withCategory(TrainingCategory trainingCategory) {
        new TrainingRequest(
                title, description, numberOfPlaces, level, skills, durationInHours, trainingCategory
        );
    }

}
