package com.web_banking_application.banking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web_banking_application.banking.dto.loanDto;
import com.web_banking_application.banking.service.loanService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/loan")
public class loanController {
	private loanService  LoanService;

	public loanController(loanService  LoanService) {
		super();
		this.LoanService = LoanService;
		// TODO Auto-generated constructor stub
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping
	public ResponseEntity<loanDto> createLoan(@RequestBody loanDto LoanDto){
		loanDto savedLoan = LoanService.createLoan(LoanDto);
		return new ResponseEntity<>(savedLoan, HttpStatus.CREATED);
	}
	
	@GetMapping("id/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<loanDto> getLoanById(@PathVariable("id") Long loanId){
		loanDto LoanDto = LoanService.getLoanById(loanId);
		return ResponseEntity.ok(LoanDto);
	}
	
	@GetMapping
	public ResponseEntity<List<loanDto>> getAllLoand(){
		List<loanDto> Loans =  LoanService.getAllLoan();
		return ResponseEntity.ok(Loans);
	}
	
	@DeleteMapping("id/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<String> deleteAccount(@PathVariable("id") Long loanId){
		LoanService.deleteLoan(loanId);
		return ResponseEntity.ok("Loan Deleted Successfully!");
	}
	
	
}
