package com.tps.loan.auth.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tps.loan.entity.Account;
import com.tps.loan.repo.AccountRepo;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final AccountRepo repo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return repo.findByUsername(username)
                .map(account ->User
                		.withUsername(account.getUsername())
                		.password(account.getPassword())
                		.build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username %s".formatted(username)));
    }
    
    @PostConstruct
    @Transactional
    void init() {
    	var account = new Account();
    	account.setUsername("admin");
    	account.setPassword(passwordEncoder.encode("123"));
    	account.setFullName("Super Admin");
    	account.setPhone("09958771997");
    	account.setEmail("admin@gmail.com");
    	
    	repo.save(account);
    }
}
