package com.api.planlist.todo.application.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.planlist.account.domain.Account;
import com.api.planlist.account.domain.service.AccountService;
import com.api.planlist.todo.application.dto.TodoDTO;
import com.api.planlist.todo.domain.Todo;
import com.api.planlist.todo.domain.service.TodoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos/")
public class TodoRestController {
	
	private final TodoService todoService;
	
	private final AccountService accountService;
	
	@GetMapping()
	public List<Todo> getTodos() {
		return todoService.getTodos();
	}
	
	@GetMapping("{todoId}/")
	public Todo getTodo(@PathVariable Long todoId) {
		return todoService.getTodo(todoId);
	}
	

	@PostMapping()
	public Todo addTodo(@RequestBody TodoDTO todoDto) {
		
		// Account Id 가지고옴
		String AccountId = todoDto.getAccountId();
		// Account 객체 가지고옴
		Account account = accountService.getAccount(AccountId);
		// todoDTO를 Todo객체로 변환
		Todo newTodo = todoDto.toDomain();
		// Todo객체 안에 아까 가져온 Account넣어줌
		newTodo.setAccount(account);
		// 완성된 Todo 저장
		return todoService.addTodo(newTodo);
	}	
	
	@PutMapping()
	public Todo modifyTodo(@RequestBody Todo todo) {
		return todoService.modifyTodo(todo);
	}
	
	@DeleteMapping("{todoId}/")
	public void deleteTodo(@PathVariable Long todoId) {
		todoService.deleteTodo(todoId);
	}
	
}
