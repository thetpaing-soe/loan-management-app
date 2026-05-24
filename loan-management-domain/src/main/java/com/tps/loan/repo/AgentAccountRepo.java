package com.tps.loan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tps.loan.entity.AgentAccount;

public interface AgentAccountRepo extends JpaRepository<AgentAccount, Long> {}
