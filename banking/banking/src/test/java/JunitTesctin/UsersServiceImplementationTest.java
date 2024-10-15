package JunitTesctin;


import com.web_banking_application.banking.dto.UsersDto;
import com.web_banking_application.banking.entities.users;
import com.web_banking_application.banking.exception.ResourceNotFoundException;
import com.web_banking_application.banking.repositories.UsersRepositories;
import com.web_banking_application.banking.service.implimentation.UsersServiceImplementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsersServiceImplementationTest {

    @Mock
    private UsersRepositories usersRepositories;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UsersServiceImplementation usersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUsers() {
        UsersDto userDto = new UsersDto();
        userDto.setPassword("password");
        users user = new users();
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(usersRepositories.save(any(users.class))).thenReturn(user);

        UsersDto result = usersService.createUsers(userDto);

        assertNotNull(result);
        verify(passwordEncoder, times(1)).encode("password");
        verify(usersRepositories, times(1)).save(any(users.class));
    }

    @Test
    void testGetUsersByID() {
        Long userId = 1L;
        users user = new users();
        when(usersRepositories.findById(userId)).thenReturn(Optional.of(user));

        UsersDto result = usersService.getUsersByID(userId);

        assertNotNull(result);
        verify(usersRepositories, times(1)).findById(userId);
    }

    @Test
    void testGetUsersByIDNotFound() {
        Long userId = 1L;
        when(usersRepositories.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> usersService.getUsersByID(userId));
    }

    @Test
    void testGetAllUsers() {
        List<users> usersList = Arrays.asList(new users(), new users());
        when(usersRepositories.findAll()).thenReturn(usersList);

        List<UsersDto> result = usersService.getAllUsers();

        assertEquals(2, result.size());
        verify(usersRepositories, times(1)).findAll();
    }

    @Test
    void testUpdateUser() {
        Long userId = 1L;
        UsersDto updatedUser = new UsersDto();
        updatedUser.setPassword("newPassword");
        users existingUser = new users();
        when(usersRepositories.findById(userId)).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");
        when(usersRepositories.save(any(users.class))).thenReturn(existingUser);

        UsersDto result = usersService.updateUser(userId, updatedUser);

        assertNotNull(result);
        verify(usersRepositories, times(1)).findById(userId);
        verify(passwordEncoder, times(1)).encode("newPassword");
        verify(usersRepositories, times(1)).save(any(users.class));
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;
        when(usersRepositories.findById(userId)).thenReturn(Optional.of(new users()));

        usersService.deleteUser(userId);

        verify(usersRepositories, times(1)).findById(userId);
        verify(usersRepositories, times(1)).deleteById(userId);
    }

    @Test
    void testRegisterUser() {
        UsersDto userDto = new UsersDto();
        userDto.setPassword("password");
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        usersService.registerUser(userDto);

        verify(passwordEncoder, times(1)).encode("password");
        verify(usersRepositories, times(1)).save(any(users.class));
    }
}
