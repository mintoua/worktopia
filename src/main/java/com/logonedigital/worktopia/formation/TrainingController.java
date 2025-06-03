package com.logonedigital.worktopia.formation;

import com.logonedigital.worktopia.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainings")
@CrossOrigin(origins = "http://localhost:4029")
public class TrainingController {

    private final TrainingService trainingService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody @Valid TrainingRequest request){
        this.trainingService.save(request);
        return ResponseEntity
                .status(200)
                .body("Training saved successfully !");
    }


    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll()
    {
        return ResponseEntity.ok(
                new ApiResponse(
                        "All Trainings Found",
                        trainingService.getAll()
                )
        );
    }


    @GetMapping("/get_by_id/{id}")
        public ResponseEntity<TrainingDTO> getById(UUID id){
        return ResponseEntity.status(200).body(this.trainingService.getById(id));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> upd(UUID id, TrainingRequest trainingRequest){
        this.trainingService.update(id, trainingRequest);
        return ResponseEntity.status(202).body("Updated sucessfully");
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<String> deletbyId(UUID id){
        this.trainingService.delete(id);
        return ResponseEntity.status(202).body("Updated sucessfully");
    }
}
