package com.tps.loan.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tps.loan.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {
	
	Optional<Account> findByUsername(String username);
}
