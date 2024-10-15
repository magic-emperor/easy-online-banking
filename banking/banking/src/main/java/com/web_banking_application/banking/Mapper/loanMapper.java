package com.web_banking_application.banking.Mapper;

import com.web_banking_application.banking.dto.loanDto;
import com.web_banking_application.banking.entities.LoanEntity;

public class loanMapper {
	public static loanDto mapToLoanDto(LoanEntity loanEntity) {
		return new loanDto(
				loanEntity.getLoan_Id(),
				loanEntity.getUser_Name(),
				loanEntity.getUser_Address(),
				loanEntity.getLoan_Type(),
				loanEntity.getAmount_Required(),
				loanEntity.getAccount_Number(),
				loanEntity.getMobile_Number(),
				loanEntity.getLoan_Status(),
				loanEntity.getUser_Id()
				);
	}
	public static LoanEntity mapToLoanEntity(loanDto LoanDto) {
		return new LoanEntity(
				LoanDto.getLoanId(),
				LoanDto.getUserName(),
				LoanDto.getUserAddress(),
				LoanDto.getLoanType(),
				LoanDto.getAmountRequired(),
				LoanDto.getAccountNumber(),
				LoanDto.getMobileNumber(),
				LoanDto.getLoanStatus(),
				LoanDto.getUserId()
				);
	}
}
