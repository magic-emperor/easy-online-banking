package com.web_banking_application.banking.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web_banking_application.banking.entities.accountEntity;

public interface accountsRepositories extends JpaRepository<accountEntity, Long>{
	@Query("SELECT a FROM accountEntity a WHERE a.userId = :userId")
	accountEntity findByUserId(@Param("userId") long userId);
	
	@Query("SELECT a FROM accountEntity a WHERE a.accNumber = :accNumber")
	accountEntity findUserByAccountNumber(@Param("accNumber") long accNumber);

}
