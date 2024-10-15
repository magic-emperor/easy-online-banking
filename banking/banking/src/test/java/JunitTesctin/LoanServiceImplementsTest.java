package JunitTesctin;


import com.web_banking_application.banking.Mapper.loanMapper;
import com.web_banking_application.banking.dto.loanDto;
import com.web_banking_application.banking.entities.LoanEntity;
import com.web_banking_application.banking.exception.ResourceNotFoundException;
import com.web_banking_application.banking.repositories.loanRepositories;
import com.web_banking_application.banking.service.implimentation.loanServiceImplements;

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

class LoanServiceImplementsTest {

    @Mock
    private loanRepositories LoanRepositories;

    @InjectMocks
    private loanServiceImplements loanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateLoan() {
        loanDto inputDto = new loanDto();
        LoanEntity entity = new LoanEntity();
        LoanEntity savedEntity = new LoanEntity();
        loanDto expectedDto = new loanDto();

        when(LoanRepositories.save(any(LoanEntity.class))).thenReturn(savedEntity);

        loanDto result = loanService.createLoan(inputDto);

        assertNotNull(result);
        verify(LoanRepositories, times(1)).save(any(LoanEntity.class));
    }

    @Test
    void testGetLoanById() {
        Long loanId = 1L;
        LoanEntity entity = new LoanEntity();
        when(LoanRepositories.findById(loanId)).thenReturn(Optional.of(entity));

        loanDto result = loanService.getLoanById(loanId);

        assertNotNull(result);
        verify(LoanRepositories, times(1)).findById(loanId);
    }

    @Test
    void testGetLoanByIdNotFound() {
        Long loanId = 1L;
        when(LoanRepositories.findById(loanId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> loanService.getLoanById(loanId));
    }

    @Test
    void testGetAllLoan() {
        List<LoanEntity> entities = Arrays.asList(new LoanEntity(), new LoanEntity());
        when(LoanRepositories.findAll()).thenReturn(entities);

        List<loanDto> result = loanService.getAllLoan();

        assertEquals(2, result.size());
        verify(LoanRepositories, times(1)).findAll();
    }

    @Test
    void testDeleteLoan() {
        Long loanId = 1L;
        when(LoanRepositories.findById(loanId)).thenReturn(Optional.of(new LoanEntity()));

        loanService.deleteLoan(loanId);

        verify(LoanRepositories, times(1)).findById(loanId);
        verify(LoanRepositories, times(1)).deleteById(loanId);
    }

    @Test
    void testDeleteLoanNotFound() {
        Long loanId = 1L;
        when(LoanRepositories.findById(loanId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> loanService.deleteLoan(loanId));
    }
}