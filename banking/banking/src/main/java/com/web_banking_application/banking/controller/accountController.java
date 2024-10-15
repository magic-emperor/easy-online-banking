package com.web_banking_application.banking.controller;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.web_banking_application.banking.dto.accountDto;
//import com.web_banking_application.banking.exception.AccountNotFoundException;
//import com.web_banking_application.banking.exception.InsufficientFundsException;
import com.web_banking_application.banking.service.accountService;


@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "http://localhost:3000")
public class accountController {
//	@Autowired
    private final accountService AccountService;

    public accountController(accountService AccountService) {
        this.AccountService = AccountService;
    }
    
    // Build Add account Rest API
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<accountDto> createAccount(@RequestBody accountDto AccountDto) {
        System.out.println("Received DTO in controller: " + AccountDto);
        accountDto savedAccount = AccountService.createAccount(AccountDto);
        System.out.println("Created Account DTO: " + savedAccount);  // Changed from createAccount(AccountDto) to savedAccount
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }
    // Build get account by userId Rest API
    @GetMapping("/userid/{userId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<accountDto> getAccountByUserID(@PathVariable("userId") Long userId) {
        accountDto accountDto = AccountService.getAccountByuserId(userId);
        if (accountDto != null) {
            return ResponseEntity.ok(accountDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //get account details using Account number
    @GetMapping("/{accNumber}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<accountDto> getAccountByAccountNumber(@PathVariable("accNumber") Long accNumber) {
        accountDto accountDto = AccountService.getAccountByAccountNumber(accNumber);
        if (accountDto != null) {
            return ResponseEntity.ok(accountDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Build get account by accId Rest API
    @GetMapping("/id/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<accountDto> getAccountByID(@PathVariable("id") Long accId) {
        accountDto AccountDto = AccountService.getAcountByID(accId);
        return ResponseEntity.ok(AccountDto);
    }

    // Build get all accounts Rest API
    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<accountDto>> getAllAccounts() {
        List<accountDto> Accounts = AccountService.getAllAccounts();
        return ResponseEntity.ok(Accounts);
    }
    
    // Build Update account Rest API
    @PutMapping("/id/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<accountDto> updateAccount(@PathVariable("id") Long accId, @RequestBody accountDto updateAccounts) {
        accountDto AccountDto = AccountService.updateAccountDetails(accId, updateAccounts);
        return ResponseEntity.ok(AccountDto);
    }
    
    @PutMapping("user/{userId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<accountDto> updateAccountByuserId(@PathVariable("userId") Long accNumber,@RequestBody accountDto updatedAccounts) {
        accountDto accountDto = AccountService.updateAccountByuserId(accNumber,updatedAccounts);
        if (accountDto != null) {
            return ResponseEntity.ok(accountDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @PutMapping("acc/{accNumber}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<accountDto> updateAccountByAccountNumber(@PathVariable("accNumber") Long accNumber,@RequestBody accountDto updateAccounts) {
        accountDto accountDto = AccountService.updateAccountByAccountNumber(accNumber,updateAccounts);
        if (accountDto != null) {
            return ResponseEntity.ok(accountDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    // Build Delete account Rest API
    @DeleteMapping("/id/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Long accId) {
        AccountService.deleteAccount(accId);
        return ResponseEntity.ok("Account Deleted Successfully!");
    }
}
