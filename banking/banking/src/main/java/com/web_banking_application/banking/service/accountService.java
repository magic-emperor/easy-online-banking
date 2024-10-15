package com.web_banking_application.banking.service;

import java.util.List;
import com.web_banking_application.banking.dto.accountDto;
//import com.web_banking_application.banking.exception.AccountNotFoundException;
//import com.web_banking_application.banking.exception.InsufficientFundsException;

public interface accountService {
    accountDto createAccount(accountDto AccountDto);
    accountDto getAcountByID(long accId);
    List<accountDto> getAllAccounts();
    accountDto updateAccountDetails(long accId, accountDto updatedAccount);
    accountDto updateAccountByuserId(long userId, accountDto updateAccountUserId);
    void deleteAccount(long accId);
    accountDto getAccountByuserId(long userId);
    accountDto getAccountByAccountNumber(long accNumber);
    accountDto updateAccountByAccountNumber(long accNumber, accountDto updateAccountByAccountNumber);
    
    // New method for fund transfer
//    void transferFunds(Long senderAccountNumber, Long receiverAccountNumber, Double amount) throws InsufficientFundsException, AccountNotFoundException;
}
