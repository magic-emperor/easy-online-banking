package com.web_banking_application.banking.service.implimentation;

import com.web_banking_application.banking.entities.users;
import com.web_banking_application.banking.repositories.UsersRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UsersRepositories usersRepositories;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        users user = usersRepositories.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        
        System.out.println("Retrieved password from DB: " + user.getPassword()); // Add this line for debugging
        
        return User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .roles("USER")
            .build();
    }
}
