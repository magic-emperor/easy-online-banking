package com.web_banking_application.banking.service;

import java.util.List;

import com.web_banking_application.banking.dto.transactionDto;

public interface transactionService {
	transactionDto createTransaction(transactionDto TransactionDto);
	List<transactionDto> getAlltransaction();
	void deleteTransaction(long transactionId);
	List<transactionDto> getAllTransctionById(long userId);
//	transactionDto getAlltransaction(Integer transactionId);
}
