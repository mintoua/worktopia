package com.logonedigital.worktopia.employe;

import com.logonedigital.worktopia.common.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(
            @RequestBody @Valid EmployeeRequest request) throws MessagingException {
        employeeService.save(request);
        return ResponseEntity.ok(
                new ApiResponse(
                        "Saved successfully",
                         ""));
    }



    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll()
    {
        return ResponseEntity.ok(
                new ApiResponse(
                        "All Employees Found",
                        employeeService.getAll()
                )
        );
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> getById(@PathVariable long id){

                        return ResponseEntity
                                .status(200).
                                body(
                                        this.employeeService.getById(id)
                                );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> upd(Long id, EmployeeRequest employeeRequest){
        this.employeeService.update(id, employeeRequest);
        return ResponseEntity.status(202).body("Updated successfully");
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<String> deletbyId(long id){
        this.employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated successfully");
    }
}
