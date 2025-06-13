package com.logonedigital.worktopia.formation;
import lombok.Builder;

import java.util.Date;

@Builder
public record TrainingCategoryDTO(
        Long dbId,
         String name,
         String description,
        String iconName
) {


    public static TrainingCategoryDTO trainingCategoryDTO(TrainingCategory trainingCategory) {

        return TrainingCategoryDTO.builder()
                .name(trainingCategory.getName())
                .dbId(trainingCategory.getDbId())
                .description(trainingCategory.getDescription())
                .iconName(trainingCategory.getIconName())
                .build();
    }
}
