package com.api.planlist.account.domain.service.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.planlist.account.domain.Account;
import com.api.planlist.account.domain.service.AccountService;
import com.api.planlist.account.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{
	
	private final AccountRepository accountRepository;
	
	@Override
	public List<Account> getAccountList() {
		List<Account> accounts = accountRepository.findAll();
		return accounts;
	}

	@Override
	public Account getAccount(String accountId) {
		Account account = accountRepository.findById(accountId).orElseThrow();
		return account;
	}

	@Override
	public Account addAccount(Account account) {
		accountRepository.save(account);
		return accountRepository.findById(account.getAccountId()).orElseThrow();
	}
	
	@Override
	public Account modifyAccount(Account account) {
		accountRepository.save(account);
		return account;
	}

	@Override
	public void deleteAccount(String accountId) {
		accountRepository.deleteById(accountId);
	}

	@Override
	public Account signinAccount(Account account) {
		return accountRepository.signin(account.getEmail(), account.getPassword());
	}

	@Override
	public Account authAccount(Account account) {
		return accountRepository.auth(account.getEmail());
	}
		
}
