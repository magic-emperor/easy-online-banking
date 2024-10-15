package com.web_banking_application.banking.service;

import java.util.List;

import com.web_banking_application.banking.dto.loanDto;

public interface loanService {
	loanDto createLoan(loanDto LoanDto);
	loanDto getLoanById(long loanId);
	List<loanDto>getAllLoan();
	void deleteLoan(long loanId);
}
