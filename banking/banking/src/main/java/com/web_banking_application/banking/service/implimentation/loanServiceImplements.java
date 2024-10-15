package com.web_banking_application.banking.service.implimentation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.web_banking_application.banking.Mapper.loanMapper;
import com.web_banking_application.banking.dto.loanDto;
import com.web_banking_application.banking.entities.LoanEntity;
import com.web_banking_application.banking.exception.ResourceNotFoundException;
import com.web_banking_application.banking.repositories.loanRepositories;
import com.web_banking_application.banking.service.loanService;

@Service
public class loanServiceImplements implements loanService{
	private loanRepositories LoanRepositories;

	public loanServiceImplements(loanRepositories LoanRepositories) {
		super();
		this.LoanRepositories = LoanRepositories;
		// TODO Auto-generated constructor stub
	}

	@Override
	public loanDto createLoan(loanDto LoanDto) {
		// TODO Auto-generated method stub
		LoanEntity loanEntity = loanMapper.mapToLoanEntity(LoanDto);
		LoanEntity saveLoan = LoanRepositories.save(loanEntity);
		return loanMapper.mapToLoanDto(saveLoan);
		
	}

	@Override
	public loanDto getLoanById(long loanId) {
		// TODO Auto-generated method stub
		LoanEntity loanEntity = LoanRepositories.findById(loanId).orElseThrow(()->new ResourceNotFoundException("Loan Not Found:"+ loanId));
		return loanMapper.mapToLoanDto(loanEntity);
	}

	@Override
	public List<loanDto> getAllLoan() {
		// TODO Auto-generated method stub
		List<LoanEntity> loanEntity = (List<LoanEntity>) LoanRepositories.findAll();
		return loanEntity.stream().map((allLoans)-> loanMapper.mapToLoanDto(allLoans)).collect(Collectors.toList());
	}

	@Override
	public void deleteLoan(long loanId) {
		// TODO Auto-generated method stub
		LoanRepositories.findById(loanId).orElseThrow(()->new ResourceNotFoundException("Loan Not Found:"+ loanId));
		LoanRepositories.deleteById(loanId);
	}
}
