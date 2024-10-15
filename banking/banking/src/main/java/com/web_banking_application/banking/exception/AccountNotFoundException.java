package com.web_banking_application.banking.exception;

public class AccountNotFoundException extends RuntimeException{
	  public AccountNotFoundException(String message) {
	        super(message);
	    }
}
