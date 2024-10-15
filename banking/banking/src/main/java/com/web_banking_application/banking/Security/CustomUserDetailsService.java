//package com.web_banking_application.banking.Security;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import com.web_banking_application.banking.entities.users;
//import com.web_banking_application.banking.repositories.UsersRepositories;
//import java.util.ArrayList;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UsersRepositories userRepository;
//
//    public CustomUserDetailsService(UsersRepositories userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        users user = userRepository.findByEmail(email)
//            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
//
//        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
//    }
//}