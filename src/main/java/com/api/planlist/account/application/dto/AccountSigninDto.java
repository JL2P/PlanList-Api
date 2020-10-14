package com.api.planlist.account.application.dto;

import org.springframework.beans.BeanUtils;

import com.api.planlist.account.domain.Account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor //파라미터가 없는 생성자를 생성
@Getter
@Setter
public class AccountSigninDto {
	private String email; 
	private String password;
	
	public AccountSigninDto(Account account) {
		BeanUtils.copyProperties(account, this);
	}
	
	public Account toDomain() {
		Account account = new Account();
		BeanUtils.copyProperties(this, account);
		return account;
	}
}
