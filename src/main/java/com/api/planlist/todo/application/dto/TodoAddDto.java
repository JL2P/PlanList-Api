package com.api.planlist.todo.application.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.api.planlist.account.application.dto.AccountDto;
import com.api.planlist.todo.domain.Todo;
import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 *	생성 : 정승훈
 * 	설명 : front에서 Todo를 추가하기 위한 데이터를 받는 객체
 * 		  Account객체에 대한 정보는 일단 accountId로 받아서
 * 		  데이터를 받아온 뒤, DB에 요청을 하는식으로 처리한다.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TodoAddDto implements Serializable {

	private String title;
	private String description;
	private String startTime;
	private String endTime;
	private String completed;
	private String openAt;
	private String accountId;
	
	public TodoAddDto(Todo todo) {
		BeanUtils.copyProperties(todo, this);
	}
	
	
	public Todo toDomain() {
		Todo todo = new Todo();
		BeanUtils.copyProperties(this, todo);
		return todo;
	}

	
}
