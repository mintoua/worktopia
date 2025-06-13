package com.logonedigital.worktopia.conge;

import com.logonedigital.worktopia.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leaves")
public class LeaveRequestController {

    private final LeaveRequestService service;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(
            @RequestBody @Valid LeaveRequestDTO request) {
        service.save(request);
        return ResponseEntity.ok(
                new ApiResponse(
                        "Saved successfully",
                        service.save(request)));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<ApiResponse> approve(@PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse(
                        "Approved successfully",
                        service.approve(id)
                )
        );
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<ApiResponse> reject(@PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse(
                        "Rejected successfully",
                        service.reject(id)
                )
        );
    }
}
