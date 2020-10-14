package com.api.planlist.account.domain.service;

import java.util.List;


import com.api.planlist.account.domain.Account;


public interface AccountService {
	
	public List<Account> getAccountList();
	public Account getAccount(String accountId);
	public Account addAccount(Account account);
	public Account modifyAccount(Account account);
	public void deleteAccount(String accountId);
	//로그인
	public Account signinAccount(Account account);
	//auth
	public Account authAccount(Account account);
	
}
