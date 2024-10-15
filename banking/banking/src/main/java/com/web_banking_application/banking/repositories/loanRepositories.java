package com.web_banking_application.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.web_banking_application.banking.entities.LoanEntity;

public interface loanRepositories extends JpaRepository<LoanEntity, Long>{
	
}
