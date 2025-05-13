package com.logonedigital.worktopia.auth;

import com.logonedigital.worktopia.common.ApiResponse;
import com.logonedigital.worktopia.user.UserService;
import com.logonedigital.worktopia.user.password.ResetPasswordRequest;
import com.logonedigital.worktopia.user.password.VerificationRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final AuthenticationService service;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticateUser, UserService userService) {
        this.service = authenticateUser;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid RegistrationRequest request) {
        service.register(request);
        return ResponseEntity.ok(new ApiResponse("Successfully registered user", null));
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request
    ){
        AuthenticationResponse response = service.authenticate(request);
        return ResponseEntity.ok(new ApiResponse("Successfully authenticated", response));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestBody @Valid ResetPasswordRequest request
    ){
        userService.resetPassword(request.email(), request.newPassword());
        return ResponseEntity.ok("Password reset successfully");
    }

    @PostMapping("/reactivate-account")
    public ResponseEntity<?> reactivateAccount(
            @RequestBody VerificationRequest request
            )
    {
        userService.reactivateAccount(request);
        return ResponseEntity.ok("Account reactivated successfully");
    }

}
