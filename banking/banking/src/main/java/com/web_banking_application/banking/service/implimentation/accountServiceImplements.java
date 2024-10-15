package com.web_banking_application.banking.service.implimentation;

import java.util.List;
import java.util.stream.Collectors;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web_banking_application.banking.Mapper.AccountMapper;
import com.web_banking_application.banking.dto.accountDto;
import com.web_banking_application.banking.entities.accountEntity;
import com.web_banking_application.banking.exception.AccountNotFoundException;
//import com.web_banking_application.banking.exception.InsufficientFundsException;
import com.web_banking_application.banking.exception.ResourceNotFoundException;
import com.web_banking_application.banking.repositories.accountsRepositories;
import com.web_banking_application.banking.service.accountService;


@Service
public class accountServiceImplements implements accountService{

	private accountsRepositories AccountsRepositories;

	public accountServiceImplements(accountsRepositories AccountsRepositories) {
		super();
		this.AccountsRepositories = AccountsRepositories;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public accountDto createAccount(accountDto AccountDto) {
	    System.out.println("Received DTO: " + AccountDto);
	    accountEntity AccountEntity = AccountMapper.mapToAccountEntity(AccountDto);
	    System.out.println("Created Entity: " + AccountEntity);
	    System.out.println("Creating account in service: " + AccountDto);
	    System.out.println("Creating account in service: " + AccountDto);
	    accountEntity savedAccount = AccountsRepositories.save(AccountEntity);
	    return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public accountDto getAcountByID(long accId) {
		// TODO Auto-generated method stub
		accountEntity AccountEntity = AccountsRepositories.findById(accId).orElseThrow(()->new ResourceNotFoundException("User Not Found:"+ accId));
		return AccountMapper.mapToAccountDto(AccountEntity);
	}

	@Override
	public List<accountDto> getAllAccounts() {
		// TODO Auto-generated method stub
		List<accountEntity> AccountEntity = (List<accountEntity>) AccountsRepositories.findAll();
		return AccountEntity.stream().map((allAccounts)-> AccountMapper.mapToAccountDto(allAccounts)).collect(Collectors.toList());
	}

	@Override
	public accountDto updateAccountDetails(long accId, accountDto updatedAccount) {
		// TODO Auto-generated method stub
		accountEntity AccountEntity = AccountsRepositories.findById(accId).orElseThrow(()->new ResourceNotFoundException("User Not Found:"+ accId));
//		AccountEntity.setAcc_Id(updatedAccount.getAccId());
		AccountEntity.setUserId(updatedAccount.getUserId());
		AccountEntity.setAccNumber(updatedAccount.getAccNumber());
		AccountEntity.setAccBalance(updatedAccount.getAccBalance());
		AccountEntity.setAccCreatedDate(updatedAccount.getAccCreatedAt());
		AccountEntity.setAccType(updatedAccount.getAccType());
		accountEntity  Account = AccountsRepositories.save(AccountEntity);
		return AccountMapper.mapToAccountDto(Account);
	}

	@Override
	public void deleteAccount(long accId) {
		// TODO Auto-generated method stub
		AccountsRepositories.findById(accId).orElseThrow(()->new ResourceNotFoundException("User Not Found:"+ accId));
		AccountsRepositories.deleteById(accId);
	}

	 @Override
	    public accountDto getAccountByuserId(long userId) {
	        accountEntity account = AccountsRepositories.findByUserId(userId);
	        if (account != null) {
	            return mapToDto(account);
	        }
	        return null;  
	    }

	    private accountDto mapToDto(accountEntity account) {
	        // Mapping entity to DTO (convert fields accordingly)
	        return new accountDto(account.getAccId(), account.getUserId(), account.getAccNumber(), account.getAccBalance(), account.getAccCreatedDate(), account.getAccType());
	    }

	    @Override
	    public accountDto updateAccountByuserId(long userId, accountDto updateAccountUserId) {
	        // Use findByUserId instead of findById
	        accountEntity AccountEntity = AccountsRepositories.findByUserId(userId);
	        
	        if (AccountEntity == null) {
	            throw new ResourceNotFoundException("Account not found for userId: " + userId);
	        }
	        
	        // Update the account details
	        AccountEntity.setAccNumber(updateAccountUserId.getAccNumber());
	        AccountEntity.setAccBalance(updateAccountUserId.getAccBalance());
	        AccountEntity.setAccCreatedDate(updateAccountUserId.getAccCreatedAt());
	        AccountEntity.setAccType(updateAccountUserId.getAccType());

	        // Save the updated account
	        accountEntity updatedAccount = AccountsRepositories.save(AccountEntity);
	        
	        return AccountMapper.mapToAccountDto(updatedAccount);
	    }

		@Override
			public accountDto getAccountByAccountNumber(long accNumber) {
			    accountEntity account = AccountsRepositories.findUserByAccountNumber(accNumber);
			    if (account != null) {
			        return mapToDto(account);
			    }
			    return null;
			}

		@Override
		public accountDto updateAccountByAccountNumber(long accNumber, accountDto updateAccountByAccountNumber) {
			// TODO Auto-generated method stub
			 accountEntity AccountEntity = AccountsRepositories.findUserByAccountNumber(accNumber);
		        
		        if (AccountEntity == null) {
		            throw new AccountNotFoundException("Account not found for userId: " + accNumber);
		        }
		        
		        // Update the account details
		        AccountEntity.setAccNumber(updateAccountByAccountNumber.getAccNumber());
		        AccountEntity.setAccBalance(updateAccountByAccountNumber.getAccBalance());
		        AccountEntity.setAccCreatedDate(updateAccountByAccountNumber.getAccCreatedAt());
		        AccountEntity.setAccType(updateAccountByAccountNumber.getAccType());

		        // Save the updated account
		        accountEntity updatedAccount = AccountsRepositories.save(AccountEntity);
		        
		        return AccountMapper.mapToAccountDto(updatedAccount);
		}

		






}

