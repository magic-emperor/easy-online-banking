package JunitTesctin;


import com.web_banking_application.banking.Security.JwtUtil;
import com.web_banking_application.banking.controller.AuthController;
import com.web_banking_application.banking.dto.AuthRequest;
import com.web_banking_application.banking.dto.AuthResponse;
import com.web_banking_application.banking.dto.UsersDto;
import com.web_banking_application.banking.service.implimentation.UsersServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private UsersServiceImplementation usersServiceImplementation;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticateSuccess() {
        AuthRequest authRequest = new AuthRequest("test@example.com", "password");
        UsersDto userDto = new UsersDto();
        userDto.setId(1L);
        userDto.setEmail("test@example.com");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(usersServiceImplementation.getAllUsers()).thenReturn(Arrays.asList(userDto));
        when(usersServiceImplementation.getUsersByID(1L)).thenReturn(userDto);
        when(jwtUtil.generateToken(eq("test@example.com"), eq(1L))).thenReturn("jwt_token");

        ResponseEntity<?> response = authController.authenticate(authRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof AuthResponse);
//        assertEquals("jwt_token", ((AuthResponse) response.getBody()).getToken());
    }

    @Test
    void testAuthenticateBadCredentials() {
        AuthRequest authRequest = new AuthRequest("test@example.com", "wrong_password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        ResponseEntity<?> response = authController.authenticate(authRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertTrue(response.getBody() instanceof String);
        assertTrue(((String) response.getBody()).startsWith("Incorrect username or password"));
    }

    @Test
    void testAuthenticateInternalError() {
        AuthRequest authRequest = new AuthRequest("test@example.com", "password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Internal error"));

        ResponseEntity<?> response = authController.authenticate(authRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody() instanceof String);
        assertTrue(((String) response.getBody()).startsWith("Authentication error"));
    }
}