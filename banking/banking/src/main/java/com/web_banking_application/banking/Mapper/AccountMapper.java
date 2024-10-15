package com.web_banking_application.banking.Mapper;

import com.web_banking_application.banking.dto.accountDto;
import com.web_banking_application.banking.entities.accountEntity;
import com.web_banking_application.banking.entities.users;

public class AccountMapper{
    public static accountDto mapToAccountDto(accountEntity accountEntity) {
        if (accountEntity == null) {
            return null;
        }
        return new accountDto(
            accountEntity.getAccId(),
            accountEntity.getUserId(),
            accountEntity.getAccNumber(),
            accountEntity.getAccBalance(),
            accountEntity.getAccCreatedDate(),
            accountEntity.getAccType()
        );
    }

    public static accountEntity mapToAccountEntity(accountDto accountDto) {
        if (accountDto == null) {
            return null;
        }
        return new accountEntity(
            accountDto.getAccId(),
            accountDto.getUserId(),
            accountDto.getAccNumber(),
            accountDto.getAccBalance(),
            accountDto.getAccCreatedAt(),
            accountDto.getAccType()
        );
    }
	public static accountEntity toDto(accountDto AccountDto) {
	    accountEntity AccountEntity = new accountEntity();
	    AccountEntity.setAccId(AccountDto.getAccId());
	    AccountEntity.setUserId(AccountDto.getUserId());
	    AccountEntity.setAccBalance(AccountDto.getAccBalance());
	    AccountEntity.setAccNumber(AccountDto.getAccNumber());
	    AccountEntity.setAccCreatedDate(AccountDto.getAccCreatedAt());
	    AccountEntity.setAccType(AccountDto.getAccType());
	    return AccountEntity;
	}

	public static accountDto toEntity(accountEntity AccountEntity) {
	    accountDto AccountDto = new accountDto(
	        AccountEntity.getAccId(),
	        AccountEntity.getUserId(),
	        AccountEntity.getAccNumber(),
	        AccountEntity.getAccBalance(),
	        AccountEntity.getAccCreatedDate(),
	        AccountEntity.getAccType()
	    );
	    return AccountDto;
	}
	
}