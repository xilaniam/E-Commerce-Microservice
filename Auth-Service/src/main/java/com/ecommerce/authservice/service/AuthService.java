package com.ecommerce.authservice.service;

import com.ecommerce.authservice.controller.AuthController;
import com.ecommerce.authservice.dto.LoginRequest;
import java.util.Optional;

import com.ecommerce.authservice.dto.SignUpRequest;
import com.ecommerce.authservice.dto.SignUpResponse;
import com.ecommerce.authservice.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final Logger log = LoggerFactory.getLogger(AuthService.class);
    public AuthService(UserService userService, PasswordEncoder passwordEncoder , JwtUtil jwtUtil){
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public Optional<String> authenticateUser(LoginRequest loginRequest){
        Optional<String> token = userService.findUserByName(loginRequest.getUsername())
                .filter(u -> passwordEncoder.matches(loginRequest.getPassword(),u.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getUsername(),u.getRole()));
        log.info("User authenticated, token generated");
        return token;
    }

    public SignUpResponse createNewUser(SignUpRequest request){
        SignUpRequest newRequest = new SignUpRequest();
        newRequest.setUsername(request.getUsername());
        newRequest.setRole(request.getRole());
        newRequest.setPassword(passwordEncoder.encode(request.getPassword()));

        return userService.createUser(newRequest);
    }

    public boolean validateToken(String token){
        try {
            jwtUtil.validateToken(token);
            return true;
        }
        catch (JwtException ex){
            return false;
        }
    }
}
