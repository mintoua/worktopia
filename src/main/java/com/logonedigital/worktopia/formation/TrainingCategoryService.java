package com.logonedigital.worktopia.formation;

import com.logonedigital.worktopia.exception.RessourceNonExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrainingCategoryService {
    private final TrainingCategoryRepository trainingCategoryRepository;

    public TrainingCategoryService(TrainingCategoryRepository trainingCategoryRepository) {
        this.trainingCategoryRepository = trainingCategoryRepository;


    }

    public UUID save(TrainingCategoryReq request){




        TrainingCategory trainingCatToSave = TrainingCategoryReq.of(request);

        return this.trainingCategoryRepository.save(trainingCatToSave).getPublicId();
    }

    public List<TrainingCategoryDTO> getAll() {

        List<TrainingCategory> trainingList = trainingCategoryRepository.findAll();

        return trainingList
                .stream()
                .map(TrainingCategoryDTO::trainingCategoryDTO)
                .toList();
    }


    public void delete(Long id) {
        TrainingCategory trainingCategory = this.trainingCategoryRepository.findById(id)
                .orElseThrow( ()->  new RessourceNonExistException("Training not found with id"));
        this.trainingCategoryRepository.delete(trainingCategory);
    }


    public TrainingCategoryDTO update(Long id, TrainingCategoryReq trainingRequest) {
        Optional<TrainingCategory> optionalTraining = trainingCategoryRepository.findById(id);
        if (optionalTraining.isPresent()) {
            TrainingCategory training = optionalTraining.get();
            training.setName(trainingRequest.name());
            training.setIconName(trainingRequest.iconName());
            training.setDescription(trainingRequest.description());


            TrainingCategory savedTraining = trainingCategoryRepository.saveAndFlush(training);
            return TrainingCategoryDTO.trainingCategoryDTO(savedTraining);
        } else {
            throw new RessourceNonExistException("Training not found with id " + id);
        }
    }
}
