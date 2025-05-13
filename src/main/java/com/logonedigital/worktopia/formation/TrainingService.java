package com.logonedigital.worktopia.formation;

import com.logonedigital.worktopia.exception.RessourceExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingCategoryRepository trainingCategoryRepository;
    public TrainingService(TrainingRepository trainingRepository, TrainingCategoryRepository trainingCategoryRepository) {
        this.trainingRepository = trainingRepository;
        this.trainingCategoryRepository = trainingCategoryRepository;
    }


    public UUID save(TrainingRequest request){
        //Check if training already exists
        if(trainingExists(request.title(), request.level())){
            throw new RessourceExistException("Training already exists");
        }

        //Check if a category exists else save
        TrainingCategory trainingCategory = Optional.ofNullable(
                this.trainingCategoryRepository.findByName(request.trainingCategory().getName()))
                .orElseGet(
                        ()->{
                            TrainingCategory newTrainingCategory = new TrainingCategory(request.trainingCategory().getName());
                            return trainingCategoryRepository.save(newTrainingCategory);
                        }
                );

        request.withCategory(trainingCategory);
        Training trainingToSave = TrainingRequest.of(request);
        return this.trainingRepository.save(trainingToSave).getPublicId();
    }



    private boolean trainingExists(String title, String level) {
        return trainingRepository.existsByTitleAndLevel(title, level);
    }
}
