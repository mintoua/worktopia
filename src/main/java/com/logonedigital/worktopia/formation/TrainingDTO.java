package com.logonedigital.worktopia.formation;
import lombok.Builder;

@Builder
public record TrainingDTO(
         String title,
         String description,
         Integer numberOfPlaces,
         String date
) {


    public static TrainingDTO trainingDTO(Training training) {

        return TrainingDTO.builder()
                .title(training.getTitle())
                .description(training.getDescription())
                .numberOfPlaces(training.getNumberOfPlaces())
                .date(training.getCreatedDate().toString())
                .build();
    }
}
