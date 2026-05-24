package com.tps.loan.entity;

import com.tps.loan.entity.consts.IdType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class AgentAccount extends Account {
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false, length = 15)
	private String dob;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 15)
	private IdType idType = IdType.NrcNew;
	
	@Column(nullable = false, length = 15)
	private String nrc;
	
	@OneToOne(mappedBy = "agentAccount")
	private Loan loan;
}
