package com.logonedigital.worktopia.formation;

import com.logonedigital.worktopia.exception.RessourceExistException;
import com.logonedigital.worktopia.exception.RessourceNonExistException;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        trainingList.forEach(training -> Hibernate.initialize(training.getCategory()));
        trainingList.forEach(training -> Hibernate.initialize(training.getFormateur()));

        return trainingList
                .stream()
                .map(TrainingDTO::trainingDTO)
                .toList();
    }


    public TrainingDTO getById(UUID id) {
        Optional<Training> trainingOpt = trainingRepository.findById(id);
        if (trainingOpt.isPresent()) {
            return TrainingDTO.trainingDTO(trainingOpt.get());
        } else {
            throw new RessourceNonExistException("Training not found with id " + id);
        }
    }

    public void delete(UUID id) {
        if (trainingRepository.existsById(id)) {
            trainingRepository.deleteById(id);
        } else {
            throw new RessourceNonExistException("Training not found with id " + id);
        }
    }

    public TrainingDTO update(UUID id, TrainingRequest trainingRequest) {
        Optional<Training> optionalTraining = trainingRepository.findById(id);
        if (optionalTraining.isPresent()) {
            Training training = optionalTraining.get();
            training.setTitle(trainingRequest.title());
            training.setDescription(trainingRequest.description());
            training.setDurationInDay(trainingRequest.durationInDay());
            training.setDurationInHours(trainingRequest.durationInHours());
            training.setLevel(trainingRequest.level());
            training.setDateDebut(trainingRequest.dateDebut());
            training.setLocation(trainingRequest.location());
            training.setPeriode(trainingRequest.periode());
            training.setNumberOfPlaces(trainingRequest.numberOfPlaces());
            training.setFichierFacultatif(trainingRequest.fichierFacultatif());
            training.setCoutFormation(trainingRequest.coutFormation());
            training.setBudgetImpute(trainingRequest.budgetImpute());
            training.setBudgetImpute(trainingRequest.budgetImpute());
            training.setInscriptionOuverte(trainingRequest.inscriptionOuverte());
            training.setCategory(trainingRequest.trainingCategory());
            training.setFormateur(trainingRequest.formateur());
            Training savedTraining = trainingRepository.saveAndFlush(training);
            return TrainingDTO.trainingDTO(savedTraining);
        } else {
            throw new RessourceNonExistException("Training not found with id " + id);
        }
    }
}
