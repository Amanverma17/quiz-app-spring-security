package com.quizapp.quizapplication.service;

import com.quizapp.quizapplication.model.Users;
import com.quizapp.quizapplication.repository.UserRepo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTservice jwttoken;

    private BCryptPasswordEncoder bEncoder = new BCryptPasswordEncoder();

    public Users register(Users user) {
        user.setPassword(bEncoder.encode(user.getPassword()));
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        return userRepo.save(user);
    }

    public String Verify(Users user) {

        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated()){
            return jwttoken.generateToken(user.getUsername());
        }
        else{
            return "fail";
        }
    }
}