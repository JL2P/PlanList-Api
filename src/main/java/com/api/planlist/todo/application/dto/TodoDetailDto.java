package com.api.planlist.todo.application.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.api.planlist.account.application.dto.AccountDto;
import com.api.planlist.account.domain.Account;
import com.api.planlist.todo.domain.Todo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
*	생성 : 정승훈
* 	설명 : Todo데이터의 모든 정보를 조회하기 위한 DTO
* 		  참조하는 Account객체의 데이터까지 가져올 수 있다.
*/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TodoDetailDto implements Serializable {

	private String title;
	private String description;
	private String startTime;
	private String endTime;
	private String completed;
	private String openAt;
	private AccountDto account;
	
	public TodoDetailDto(Todo todo) {
		BeanUtils.copyProperties(todo, this);
	}
	
	
	@Override
	public String toString() {
		Gson json = new Gson();
		return json.toJson(this);
	}
	

}
