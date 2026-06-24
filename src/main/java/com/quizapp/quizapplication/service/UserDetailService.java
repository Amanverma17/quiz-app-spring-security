package com.quizapp.quizapplication.service;

import com.quizapp.quizapplication.model.UserPrinciples;
import com.quizapp.quizapplication.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import com.quizapp.quizapplication.model.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user= userRepo.findByUsername(username);

        if(user == null){
            System.out.println("User not Fount");
            throw new UsernameNotFoundException("User not Fount");
        }

        return new UserPrinciples(user);
    }
}
