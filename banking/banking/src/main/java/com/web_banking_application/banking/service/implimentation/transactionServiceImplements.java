package com.web_banking_application.banking.service.implimentation;

//import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.web_banking_application.banking.Mapper.transactionMapper;
import com.web_banking_application.banking.dto.transactionDto;
import com.web_banking_application.banking.entities.transactionEntity;
import com.web_banking_application.banking.repositories.transactionRepositories;
import com.web_banking_application.banking.service.transactionService;

@Service
public class transactionServiceImplements implements transactionService{
	private transactionRepositories TransactionRepositories;
	public transactionServiceImplements(transactionRepositories TransactionRepositories) {
		super();
		this.TransactionRepositories = TransactionRepositories;
		// TODO Auto-generated constructor stub
	}
	@Override
	public transactionDto createTransaction(transactionDto TransactionDto) {
	    transactionEntity TransactionEntity = transactionMapper.mapToTransactionEntity(TransactionDto);
	    transactionEntity savedTransaction = TransactionRepositories.save(TransactionEntity);
	    return transactionMapper.mapToTransactionDto(savedTransaction); // The saved transaction should now have the ID generated
	}

	@Override
	public List<transactionDto> getAlltransaction() {
		// TODO Auto-generated method stub
		List<transactionEntity> TransactionEntity = (List<transactionEntity>) TransactionRepositories.findAll(); 
		return TransactionEntity.stream().map((allTransaction)->transactionMapper.mapToTransactionDto(allTransaction)).collect(Collectors.toList());
	}
	@Override
	public void deleteTransaction(long transactionId) {
		// TODO Auto-generated method stub
//		TransactionRepositories.findById((long) transactionId);
		TransactionRepositories.deleteById((long) transactionId);
		
	}

    @Override
    public List<transactionDto> getAllTransctionById(long userId) {
        List<transactionEntity> transactionEntities = TransactionRepositories.findAllByTransactionId(userId);
//        List<transactionDto> transactionDtos = new ArrayList<>();
		return transactionEntities.stream().map((allTransaction)->transactionMapper.mapToTransactionDto(allTransaction)).collect(Collectors.toList());
    }

}
