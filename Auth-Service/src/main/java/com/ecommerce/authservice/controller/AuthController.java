package com.ecommerce.authservice.controller;

import com.ecommerce.authservice.dto.LoginRequest;
import com.ecommerce.authservice.dto.LoginResponse;
import com.ecommerce.authservice.dto.SignUpRequest;
import com.ecommerce.authservice.dto.SignUpResponse;
import com.ecommerce.authservice.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController {
    private AuthService authService;
    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        Optional<String> tokenOPTIONAL = authService.authenticateUser(request);

        if(tokenOPTIONAL.isEmpty()){
            log.info(request.getUsername() + " " + request.getPassword());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = tokenOPTIONAL.get();
        return ResponseEntity.ok().body(new LoginResponse(token));
    }

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest request){
        SignUpResponse response = authService.createNewUser(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/validate")
    public ResponseEntity<Void> validate(@RequestHeader("Authorization") String authHeader){
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return authService.validateToken(authHeader.substring(7)) ?
                ResponseEntity.ok().build():
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
