package com.web_banking_application.banking.Mapper;

import com.web_banking_application.banking.dto.transactionDto;
import com.web_banking_application.banking.entities.transactionEntity;

public class transactionMapper {
    public static transactionDto mapToTransactionDto(transactionEntity TransactionEntity) {
        return new transactionDto(
            TransactionEntity.getTransaction_Id(),
            TransactionEntity.getUser_Id(),
            TransactionEntity.getTransaction_Date(),
            TransactionEntity.getTransaction_Details(),
            TransactionEntity.getAmount_transfered(),
            TransactionEntity.getBalance()
        );
    }

    public static transactionEntity mapToTransactionEntity(transactionDto TransactionDto) {
        return new transactionEntity(
            null, // Pass null to auto-generate transactionID
            TransactionDto.getUserId(),
            TransactionDto.getTransferDate(),
            TransactionDto.getTransferDetails(),
            TransactionDto.getAmountTransfered(),
            TransactionDto.getBalance()
        );
    }
}
