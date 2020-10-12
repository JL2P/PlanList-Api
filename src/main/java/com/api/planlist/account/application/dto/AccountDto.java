package com.api.planlist.account.application.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.api.planlist.account.domain.Account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto implements Serializable{

	private String accountId;
	private String name;  
	private String email; 
	private String password;
	private String birth;
	private String gender;
	private String address;
	private String phone;
	private String introduce;
	private int rating;
	private String usedAt;
	private String displayAt;
	
	
	public AccountDto(Account acocunt) {
		BeanUtils.copyProperties(acocunt, this);
	}
	
}
