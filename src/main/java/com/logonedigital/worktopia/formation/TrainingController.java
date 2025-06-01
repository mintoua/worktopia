package com.logonedigital.worktopia.formation;

import com.logonedigital.worktopia.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
