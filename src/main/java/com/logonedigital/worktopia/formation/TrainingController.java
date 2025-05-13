package com.logonedigital.worktopia.formation;

import com.logonedigital.worktopia.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainings")
public class TrainingController {

    private final TrainingService trainingService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(
            @RequestBody @Valid TrainingRequest request)
    {
        return ResponseEntity.ok(
                new ApiResponse(
                        "Saved successfully",
                        "PublicId: " + trainingService.save(request))
        );
    }
}
