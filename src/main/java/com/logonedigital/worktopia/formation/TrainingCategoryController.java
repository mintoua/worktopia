package com.logonedigital.worktopia.formation;

import com.logonedigital.worktopia.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
//@CrossOrigin(origins = "*")
public class TrainingCategoryController {

    private final TrainingCategoryService trainingCategoryService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(@RequestBody @Valid TrainingCategoryReq request){
        this.trainingCategoryService.save(request);
        return ResponseEntity.ok(new ApiResponse("Category saved successfully !", null));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll()
    {
        return ResponseEntity.ok(
                new ApiResponse(
                        "All Catgeories Found",
                        trainingCategoryService.getAll()
                )
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> upd(@PathVariable Long id, @RequestBody @Valid TrainingCategoryReq trainingCategoryReq){
        this.trainingCategoryService.update(id, trainingCategoryReq);
        return ResponseEntity.ok(new ApiResponse("Updated successfully", null));


    }

@DeleteMapping("/delete/{id}")
public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
    this.trainingCategoryService.delete(id);
    // Envoyer un message de succès dans ApiResponse
    return ResponseEntity.ok(new ApiResponse("Deleted successfully", null));
}
}
