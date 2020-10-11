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
		
		// Account Id �������
		String AccountId = todoDto.getAccountId();
		// Account ��ü �������
		Account account = accountService.getAccount(AccountId);
		// todoDTO�� Todo��ü�� ��ȯ
		Todo newTodo = todoDto.toDomain();
		// Todo��ü �ȿ� �Ʊ� ������ Account�־���
		newTodo.setAccount(account);
		// �ϼ��� Todo ����
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
