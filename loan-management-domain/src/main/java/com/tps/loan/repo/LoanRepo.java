package com.tps.loan.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tps.loan.entity.Loan;

public interface LoanRepo extends JpaRepository<Loan, UUID> {}
