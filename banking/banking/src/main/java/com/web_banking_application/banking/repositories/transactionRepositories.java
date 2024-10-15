package com.web_banking_application.banking.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web_banking_application.banking.entities.transactionEntity;

public interface transactionRepositories extends JpaRepository<transactionEntity, Long>{

	@Query("SELECT a FROM transactionEntity a WHERE a.user_Id = :userId")
    List<transactionEntity> findAllByTransactionId(@Param("userId") long userId);

//	transactionEntity findById(long userId);
}
