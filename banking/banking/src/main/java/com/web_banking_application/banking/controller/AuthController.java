//package com.web_banking_application.banking.controller;
//
//import com.web_banking_application.banking.Security.JwtUtil;
//import com.web_banking_application.banking.dto.AuthRequest;
//import com.web_banking_application.banking.dto.AuthResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.*;

//
//@RestController
//@RequestMapping("/api/authenticate")
//public class AuthController {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private JwtUtil jwtUtil;
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @PostMapping
//    @CrossOrigin("http://localhost:3000")
//    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
//        try {
//            authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
//            );
//            final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
//            final String jwt = jwtUtil.generateToken(userDetails.getUsername(), authRequest.getUserId());
//            return ResponseEntity.ok(new AuthResponse(jwt));
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password: " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication error: " + e.getMessage());
//        }
//    }
//}
package com.web_banking_application.banking.controller;

import com.web_banking_application.banking.Security.JwtUtil;
import com.web_banking_application.banking.dto.AuthRequest;
import com.web_banking_application.banking.dto.AuthResponse;
import com.web_banking_application.banking.dto.UsersDto;
import com.web_banking_application.banking.service.implimentation.UsersServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authenticate")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UsersServiceImplementation usersServiceImplementation;

    @PostMapping
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            final UsersDto user = usersServiceImplementation.getUsersByID(usersServiceImplementation.getAllUsers().stream()
                    .filter(u -> u.getEmail().equals(authRequest.getUsername()))
                    .findFirst()
                    .orElseThrow(() -> new UsernameNotFoundException("User not found")).getId());
            final long userId = user.getId();
            final String jwt = jwtUtil.generateToken(authRequest.getUsername(), userId);
            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication error: " + e.getMessage());
        }
    }
}

