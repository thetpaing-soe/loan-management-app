package com.tps.loan.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.tps.loan.auth.input.LoginForm;
import com.tps.loan.auth.output.AuthResult;
import com.tps.loan.auth.utils.AuthTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthLoginService {
	
	private final AuthTokenProvider tokenProvider;
	private final AuthenticationManager authenticationManager;
	
	public AuthResult login(LoginForm form) {
		try {
			var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.username(), form.password()));
			
			return new AuthResult(null, authentication.getName(), null, tokenProvider.generate(authentication.getName()));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
