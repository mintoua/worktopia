package com.logonedigital.worktopia.auth;

import com.logonedigital.worktopia.security.JwtService;
import com.logonedigital.worktopia.user.Role;
import com.logonedigital.worktopia.user.User;
import com.logonedigital.worktopia.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void register(RegistrationRequest request){
        var user =  User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        //TODO: Send email to user to confirm his account
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user  = userRepository.findByEmail(request.email())
                .orElseThrow(()-> new RuntimeException("User not found"));
        if(!user.isEnabled()){
            //TODO: Good exception handling
            throw new RuntimeException("Account not verified. Please check your email for verification link");
        }

        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();
    }


}
