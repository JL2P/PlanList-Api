package com.api.planlist.account.application.dto;

import java.io.Serializable;

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
public class AccountSignupDto implements Serializable{
	
	private String accountId;
	private String name;  
	private String email; 
	private String password;
	private String birth;
	
	public AccountSignupDto(Account account) {
		BeanUtils.copyProperties(account, this);
	}
	
	public Account toDomain(){
		Account account = new Account();
		BeanUtils.copyProperties(this,account);
		return account;
		
	}
	
}
