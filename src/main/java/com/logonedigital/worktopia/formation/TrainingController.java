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
    public ResponseEntity<ApiResponse> save(@RequestBody @Valid TrainingRequest request){
        this.trainingService.save(request);
        return ResponseEntity.ok(new ApiResponse("All Trainings Found", null));
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
        public ResponseEntity<TrainingDTO> getById(@PathVariable Long id){
        return ResponseEntity
                .status(200).body(this.trainingService.getById(id));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> upd(@PathVariable Long id, @RequestBody @Valid TrainingRequest trainingRequest){
        this.trainingService.update(id, trainingRequest);
        return ResponseEntity.ok(new ApiResponse("Update successfully", null));
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deletbyId(@PathVariable Long id){
//        this.trainingService.delete(id);
//        return ResponseEntity.ok("Deleted successfully");
//    }
@DeleteMapping("/delete/{id}")
public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
    this.trainingService.delete(id);
    // Envoyer un message de succès dans ApiResponse
    return ResponseEntity.ok(new ApiResponse("Deleted successfully", null));
}
}
