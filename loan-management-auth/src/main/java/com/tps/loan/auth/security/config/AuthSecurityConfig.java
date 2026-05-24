package com.tps.loan.auth.security.config;

import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import com.tps.loan.auth.utils.AuthTokenProvider;

@Configuration
public class AuthSecurityConfig {
	
	@Value("${app.jwt.secretKey}")
	private String secretKey;

	@Value("${app.jwt.issuer}")
	private String issuer;
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) {
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		return http
				.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.disable())
				.authorizeHttpRequests(request -> {
					request.requestMatchers("/auth/login", "/error").permitAll();
					request.anyRequest().authenticated();
					})
				.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder())))
				.build();
	}
	
	@Bean
	AuthTokenProvider tokenProvider() {
		return new AuthTokenProvider(secretKey, issuer);
	}
	
	@Bean
	JwtDecoder jwtDecoder() {
		var secretKeySpes = new SecretKeySpec(Base64.getDecoder().decode(secretKey), "HmacSHA512");
		
		return NimbusJwtDecoder
				.withSecretKey(secretKeySpes)
				.macAlgorithm(MacAlgorithm.HS512)
				.build();
	}
	
}
