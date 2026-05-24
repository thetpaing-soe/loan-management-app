package com.tps.loan.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tps.loan.entity.embeddable.LoanFees;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Embedded
	private LoanFees fees;
	
	@OneToOne
	private AgentAccount agentAccount;
	
	private LocalDateTime issuedAt;
	private LocalDateTime dueDate;
}
