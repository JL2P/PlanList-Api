package com.api.planlist.todo.application.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.BeanUtils;
import com.api.planlist.todo.domain.Todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 	생성 : 정승훈
 *	설명 : 기본적인 TodoDto
 *		   TodoDomain과 동일한 형태로 가져온다
 *		   대신 Account객체가 아닌 AccountId값으로 가져온다.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TodoDto {
	
	private String accountId;
	private String title;
	private String description;
	private String startTime;
	private String endTime;
	private String completed;
	private String openAt;

	public TodoDto(Todo todo) {
		BeanUtils.copyProperties(todo, this);
	}

	public Todo toDomain() {
		Todo todo = new Todo();
		BeanUtils.copyProperties(this, todo);
		return todo;
	}

}
