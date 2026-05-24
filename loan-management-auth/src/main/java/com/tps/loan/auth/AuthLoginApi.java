package com.tps.loan.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tps.loan.auth.input.LoginForm;
import com.tps.loan.auth.output.AuthResult;
import com.tps.loan.auth.service.AuthLoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthLoginApi {
	
	private final AuthLoginService service;
	
	@PostMapping("login")
	public ResponseEntity<AuthResult> login(LoginForm form) {
		return ResponseEntity.ok(service.login(form));
	}
	
	@GetMapping("protected")
	public ResponseEntity<String> protectedApi() {
		return ResponseEntity.ok("Success!");
	}
}
