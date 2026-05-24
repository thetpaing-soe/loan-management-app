package com.tps.loan.auth.output;

public record AuthResult(
		String fullName,
		String username,
		String phoneNumber,
		String accessToken
		) {}
