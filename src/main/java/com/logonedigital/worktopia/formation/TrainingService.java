package com.logonedigital.worktopia.formation;

import com.logonedigital.worktopia.exception.RessourceExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingCategoryRepository trainingCategoryRepository;
    private final FormateurRepository formateurRepository;
    public TrainingService(TrainingRepository trainingRepository, TrainingCategoryRepository trainingCategoryRepository, FormateurRepository formateurRepository) {
        this.trainingRepository = trainingRepository;
        this.trainingCategoryRepository = trainingCategoryRepository;
        this.formateurRepository = formateurRepository;
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

        //Check if a category exists else save
        Formateur formateur = Optional.ofNullable(
                        this.formateurRepository.findByNomFormateur(request.formateur().getNomFormateur()))
                .orElseGet(
                        ()->{
                            Formateur newFormateur = new Formateur(
                                    request.formateur().getNomFormateur(),
                                    request.formateur().getTelephoneFormateur(),
                                    request.formateur().getEmailFormateur(),
                                    request.formateur().getTypeFormateur()
                                    );

                            return formateurRepository.save(newFormateur);
                        }
                );

        request.withCategory(trainingCategory, formateur);
        Training trainingToSave = TrainingRequest.of(request);
        return this.trainingRepository.save(trainingToSave).getPublicId();
    }


    private boolean trainingExists(String title, String level) {
        return trainingRepository.existsByTitleAndLevel(title, level);
    }

    public List<TrainingDTO> getAll() {

        List<Training> trainingList = trainingRepository.findAll();
        return trainingList
                .stream()
                .map(TrainingDTO::from)
                .toList();
    }
}
