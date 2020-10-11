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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"1. Todo"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos/")
public class TodoRestController {
	
	private final TodoService todoService;
	
	private final AccountService accountService;
	
	@ApiOperation(value="TODO ����Ʈ ��ȸ", notes="��� TODO�� ��ȸ�Ѵ�.")
	@GetMapping()
	public List<Todo> getTodos() {
		return todoService.getTodos();
	}
	
	@ApiOperation(value="TODO ������ ���� ��ȸ", notes="todoId���� �̿��Ͽ� ��ȸ�Ѵ�.")
	@GetMapping("{todoId}/")
	public Todo getTodo(@PathVariable Long todoId) {
		return todoService.getTodo(todoId);
	}
	
	
	@ApiOperation(value="TODO �߰�", notes="TodoDTOŸ���� �̿��Ͽ� �����͸� �޾ƿ´�.")
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
	
	@ApiOperation(value="TODO ����", notes="�ۼ��Ǿ��ִ� TODO�� �����Ѵ�.")
	@PutMapping()
	public Todo modifyTodo(@RequestBody Todo todo) {
		return todoService.modifyTodo(todo);
	}
	
	@ApiOperation(value="TODO ����", notes="TODO�� �����Ѵ�.")
	@DeleteMapping("{todoId}/")
	public void deleteTodo(@PathVariable Long todoId) {
		todoService.deleteTodo(todoId);
	}
	
}
