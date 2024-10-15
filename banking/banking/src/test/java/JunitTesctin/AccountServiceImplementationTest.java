package JunitTesctin;


import com.web_banking_application.banking.dto.accountDto;
import com.web_banking_application.banking.entities.accountEntity;
import com.web_banking_application.banking.repositories.accountsRepositories;
import com.web_banking_application.banking.service.implimentation.accountServiceImplements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceImplementationTest {

    @Mock
    private accountsRepositories accountsRepositories;

    @InjectMocks
    private accountServiceImplements accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount() {
        accountDto dto = new accountDto();
        accountEntity entity = new accountEntity();
        when(accountsRepositories.save(any(accountEntity.class))).thenReturn(entity);

        accountDto result = accountService.createAccount(dto);

        assertNotNull(result);
        verify(accountsRepositories, times(1)).save(any(accountEntity.class));
    }

    @Test
    void testGetAccountByID() {
        Long accId = 1L;
        accountEntity entity = new accountEntity();
        when(accountsRepositories.findById(accId)).thenReturn(Optional.of(entity));

        accountDto result = accountService.getAcountByID(accId);

        assertNotNull(result);
        verify(accountsRepositories, times(1)).findById(accId);
    }

    @Test
    void testGetAllAccounts() {
        List<accountEntity> entities = Arrays.asList(new accountEntity(), new accountEntity());
        when(accountsRepositories.findAll()).thenReturn(entities);

        List<accountDto> result = accountService.getAllAccounts();

        assertEquals(2, result.size());
        verify(accountsRepositories, times(1)).findAll();
    }

    @Test
    void testUpdateAccountDetails() {
        Long accId = 1L;
        accountDto dto = new accountDto();
        accountEntity entity = new accountEntity();
        when(accountsRepositories.findById(accId)).thenReturn(Optional.of(entity));
        when(accountsRepositories.save(any(accountEntity.class))).thenReturn(entity);

        accountDto result = accountService.updateAccountDetails(accId, dto);

        assertNotNull(result);
        verify(accountsRepositories, times(1)).findById(accId);
        verify(accountsRepositories, times(1)).save(any(accountEntity.class));
    }

    @Test
    void testUpdateAccountByuserId() {
        Long userId = 1L;
        accountDto dto = new accountDto();
        accountEntity entity = new accountEntity();
        when(accountsRepositories.findByUserId(userId)).thenReturn(entity);
        when(accountsRepositories.save(any(accountEntity.class))).thenReturn(entity);

        accountDto result = accountService.updateAccountByuserId(userId, dto);

        assertNotNull(result);
        verify(accountsRepositories, times(1)).findByUserId(userId);
        verify(accountsRepositories, times(1)).save(any(accountEntity.class));
    }

    @Test
    void testDeleteAccount() {
        Long accId = 1L;
        doNothing().when(accountsRepositories).deleteById(accId);

        accountService.deleteAccount(accId);

        verify(accountsRepositories, times(1)).deleteById(accId);
    }

    @Test
    void testGetAccountByuserId() {
        Long userId = 1L;
        accountEntity entity = new accountEntity();
        when(accountsRepositories.findByUserId(userId)).thenReturn(entity);

        accountDto result = accountService.getAccountByuserId(userId);

        assertNotNull(result);
        verify(accountsRepositories, times(1)).findByUserId(userId);
    }

    @Test
    void testGetAccountByAccountNumber() {
        Long accNumber = 1234567890L;
        accountEntity entity = new accountEntity();
        when(accountsRepositories.findUserByAccountNumber(accNumber)).thenReturn(entity);

        accountDto result = accountService.getAccountByAccountNumber(accNumber);

        assertNotNull(result);
        verify(accountsRepositories, times(1)).findUserByAccountNumber(accNumber);
    }

    @Test
    void testUpdateAccountByAccountNumber() {
        Long accNumber = 1234567890L;
        accountDto dto = new accountDto();
        accountEntity entity = new accountEntity();
        when(accountsRepositories.findUserByAccountNumber(accNumber)).thenReturn(entity);
        when(accountsRepositories.save(any(accountEntity.class))).thenReturn(entity);

        accountDto result = accountService.updateAccountByAccountNumber(accNumber, dto);

        assertNotNull(result);
        verify(accountsRepositories, times(1)).findUserByAccountNumber(accNumber);
        verify(accountsRepositories, times(1)).save(any(accountEntity.class));
    }
}