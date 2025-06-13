package com.logonedigital.worktopia.formation;
import lombok.Builder;

@Builder
public record TrainingCategoryReq(

         String name,
         String description,
         String iconName
)

    {
        public static TrainingCategory of(TrainingCategoryReq request) {
        TrainingCategory trainingCat = new TrainingCategory();
        trainingCat.setName(request.name());
            trainingCat.setIconName(request.iconName());
        trainingCat.setDescription(request.description());



        return trainingCat;
    }




}
