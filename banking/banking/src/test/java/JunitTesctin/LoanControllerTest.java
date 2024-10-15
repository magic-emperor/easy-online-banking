package JunitTesctin;


import com.web_banking_application.banking.controller.loanController;
import com.web_banking_application.banking.dto.loanDto;
import com.web_banking_application.banking.service.loanService;
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

class LoanControllerTest {

    @Mock
    private loanService LoanService;

    @InjectMocks
    private loanController LoanController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateLoan() {
        loanDto inputDto = new loanDto();
        loanDto savedDto = new loanDto();
        when(LoanService.createLoan(inputDto)).thenReturn(savedDto);

        ResponseEntity<loanDto> response = LoanController.createLoan(inputDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedDto, response.getBody());
        verify(LoanService, times(1)).createLoan(inputDto);
    }

    @Test
    void testGetLoanById() {
        Long loanId = 1L;
        loanDto loanDto = new loanDto();
        when(LoanService.getLoanById(loanId)).thenReturn(loanDto);

        ResponseEntity<loanDto> response = LoanController.getLoanById(loanId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loanDto, response.getBody());
        verify(LoanService, times(1)).getLoanById(loanId);
    }

    @Test
    void testGetAllLoans() {
        List<loanDto> loans = Arrays.asList(new loanDto(), new loanDto());
        when(LoanService.getAllLoan()).thenReturn(loans);

        ResponseEntity<List<loanDto>> response = LoanController.getAllLoand();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loans, response.getBody());
        verify(LoanService, times(1)).getAllLoan();
    }

    @Test
    void testDeleteLoan() {
        Long loanId = 1L;
        doNothing().when(LoanService).deleteLoan(loanId);

        ResponseEntity<String> response = LoanController.deleteAccount(loanId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Loan Deleted Successfully!", response.getBody());
        verify(LoanService, times(1)).deleteLoan(loanId);
    }
}
