package com.api.planlist.account.application.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.planlist.account.domain.Account;
import com.api.planlist.account.domain.service.AccountService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts/")
public class AccountController {

	private final AccountService accountService;
	
	@GetMapping()
	public List<Account> getAccounts() {
		return accountService.getAccountList();
	}
	
	@GetMapping("{accountId}/")
	public Account getAccount(@PathVariable String accountId) {
		return accountService.getAccount(accountId);
	}
	
	@PostMapping("signup/")
	public Account addAccount(@RequestBody Account account) {
		return accountService.addAccount(account);
	}
	
	@PutMapping("edit/")
	public Account modifyAccount(@RequestBody Account account) {
		return accountService.modifyAccount(account);
	}
	
	@DeleteMapping("signout/{accountId}/")
	public void deleteAccount(@PathVariable String accountId) {
		accountService.deleteAccount(accountId);
	}
	//로그인
	@PostMapping("signin/")
	public Account signinAccount(@RequestBody Account account){
		System.out.println("account : " + account);
		return accountService.signinAccount(account);
	}
}
