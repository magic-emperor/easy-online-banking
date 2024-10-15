package com.web_banking_application.banking.repositories;

import com.web_banking_application.banking.dto.UsersDto;
import com.web_banking_application.banking.entities.users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsersRepositories extends JpaRepository<users, Long> {
    Optional<users> findByEmail(String email);

	void save(UsersDto users);
}