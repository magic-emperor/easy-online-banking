package JunitTesctin;


import com.web_banking_application.banking.controller.accountController;
import com.web_banking_application.banking.dto.accountDto;
import com.web_banking_application.banking.service.accountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @Mock
    private accountService AccountService;

    @InjectMocks
    private accountController AccountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount() {
        accountDto inputDto = new accountDto();
        accountDto savedDto = new accountDto();
        when(AccountService.createAccount(inputDto)).thenReturn(savedDto);

        ResponseEntity<accountDto> response = AccountController.createAccount(inputDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedDto, response.getBody());
        verify(AccountService, times(1)).createAccount(inputDto);
    }

    @Test
    void testGetAccountByUserID() {
        Long userId = 1L;
        accountDto accountDto = new accountDto();
        when(AccountService.getAccountByuserId(userId)).thenReturn(accountDto);

        ResponseEntity<accountDto> response = AccountController.getAccountByUserID(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountDto, response.getBody());
        verify(AccountService, times(1)).getAccountByuserId(userId);
    }

    @Test
    void testGetAccountByAccountNumber() {
        Long accNumber = 1234567890L;
        accountDto accountDto = new accountDto();
        when(AccountService.getAccountByAccountNumber(accNumber)).thenReturn(accountDto);

        ResponseEntity<accountDto> response = AccountController.getAccountByAccountNumber(accNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountDto, response.getBody());
        verify(AccountService, times(1)).getAccountByAccountNumber(accNumber);
    }

    @Test
    void testGetAccountByID() {
        Long accId = 1L;
        accountDto accountDto = new accountDto();
        when(AccountService.getAcountByID(accId)).thenReturn(accountDto);

        ResponseEntity<accountDto> response = AccountController.getAccountByID(accId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountDto, response.getBody());
        verify(AccountService, times(1)).getAcountByID(accId);
    }

    @Test
    void testGetAllAccounts() {
        List<accountDto> accounts = Arrays.asList(new accountDto(), new accountDto());
        when(AccountService.getAllAccounts()).thenReturn(accounts);

        ResponseEntity<List<accountDto>> response = AccountController.getAllAccounts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accounts, response.getBody());
        verify(AccountService, times(1)).getAllAccounts();
    }

    @Test
    void testUpdateAccount() {
        Long accId = 1L;
        accountDto updateDto = new accountDto();
        accountDto updatedDto = new accountDto();
        when(AccountService.updateAccountDetails(accId, updateDto)).thenReturn(updatedDto);

        ResponseEntity<accountDto> response = AccountController.updateAccount(accId, updateDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedDto, response.getBody());
        verify(AccountService, times(1)).updateAccountDetails(accId, updateDto);
    }

    @Test
    void testUpdateAccountByuserId() {
        Long userId = 1L;
        accountDto updateDto = new accountDto();
        accountDto updatedDto = new accountDto();
        when(AccountService.updateAccountByuserId(userId, updateDto)).thenReturn(updatedDto);

        ResponseEntity<accountDto> response = AccountController.updateAccountByuserId(userId, updateDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedDto, response.getBody());
        verify(AccountService, times(1)).updateAccountByuserId(userId, updateDto);
    }

    @Test
    void testUpdateAccountByAccountNumber() {
        Long accNumber = 1234567890L;
        accountDto updateDto = new accountDto();
        accountDto updatedDto = new accountDto();
        when(AccountService.updateAccountByAccountNumber(accNumber, updateDto)).thenReturn(updatedDto);

        ResponseEntity<accountDto> response = AccountController.updateAccountByAccountNumber(accNumber, updateDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedDto, response.getBody());
        verify(AccountService, times(1)).updateAccountByAccountNumber(accNumber, updateDto);
    }

    @Test
    void testDeleteAccount() {
        Long accId = 1L;
        doNothing().when(AccountService).deleteAccount(accId);

        ResponseEntity<String> response = AccountController.deleteAccount(accId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Account Deleted Successfully!", response.getBody());
        verify(AccountService, times(1)).deleteAccount(accId);
    }
}
