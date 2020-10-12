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
