package JunitTesctin;


import com.web_banking_application.banking.controller.UsersController;
import com.web_banking_application.banking.dto.UsersDto;
import com.web_banking_application.banking.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UsersControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        UsersDto userDto = new UsersDto();
        doNothing().when(userService).registerUser(userDto);

        ResponseEntity<?> response = usersController.registerUser(userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());
        verify(userService, times(1)).registerUser(userDto);
    }

    @Test
    void testCreateUser() {
        UsersDto userDto = new UsersDto();
        UsersDto savedUser = new UsersDto();
        when(userService.createUsers(userDto)).thenReturn(savedUser);

        ResponseEntity<UsersDto> response = usersController.createUser(userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedUser, response.getBody());
        verify(userService, times(1)).createUsers(userDto);
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        UsersDto userDto = new UsersDto();
        when(userService.getUsersByID(userId)).thenReturn(userDto);

        ResponseEntity<UsersDto> response = usersController.getUsersByID(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
        verify(userService, times(1)).getUsersByID(userId);
    }

    @Test
    void testGetAllUsers() {
        List<UsersDto> users = Arrays.asList(new UsersDto(), new UsersDto());
        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<UsersDto>> response = usersController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testUpdateUser() {
        Long userId = 1L;
        UsersDto updatedUser = new UsersDto();
        UsersDto returnedUser = new UsersDto();
        when(userService.updateUser(userId, updatedUser)).thenReturn(returnedUser);

        ResponseEntity<UsersDto> response = usersController.updateUsers(userId, updatedUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(returnedUser, response.getBody());
        verify(userService, times(1)).updateUser(userId, updatedUser);
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;
        doNothing().when(userService).deleteUser(userId);

        ResponseEntity<String> response = usersController.deleteUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User Deleted Successfully!", response.getBody());
        verify(userService, times(1)).deleteUser(userId);
    }
}
