package com.ecommerce.authservice.service;

import com.ecommerce.authservice.dto.SignUpRequest;
import com.ecommerce.authservice.dto.SignUpResponse;
import com.ecommerce.authservice.exception.UsernameAlreadyExistsException;
import com.ecommerce.authservice.model.User;
import com.ecommerce.authservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> findUserByName(String username){
        return userRepository.findUserByUsername(username);
    }

    public SignUpResponse createUser(SignUpRequest req){
        if(userRepository.existsByUsername(req.getUsername())){
            throw new UsernameAlreadyExistsException(req.getUsername() + " already Exists. Try another .");
        }

        User newUser = toUser(req);
        newUser = userRepository.save(newUser);
        return toSignUpDTO(newUser);
    }
    private User toUser(SignUpRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        return user;
    }

    private SignUpResponse toSignUpDTO(User user) {
        SignUpResponse response = new SignUpResponse();
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        return response;
    }
}
