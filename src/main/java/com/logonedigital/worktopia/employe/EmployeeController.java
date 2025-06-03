package com.logonedigital.worktopia.employe;

import com.logonedigital.worktopia.common.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
                        employeeService.getAllEmployees()
                )
        );
    }
}
