package com.api.planlist.todo.application.web;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.planlist.account.application.dto.AccountDto;
import com.api.planlist.account.domain.Account;
import com.api.planlist.account.domain.service.AccountService;
import com.api.planlist.common.model.response.ListResult;
import com.api.planlist.common.model.service.ResponseService;
import com.api.planlist.todo.application.dto.TodoDetailDto;
import com.api.planlist.todo.application.dto.TodoDto;
import com.api.planlist.todo.domain.Todo;
import com.api.planlist.todo.domain.service.TodoService;
import com.google.gson.Gson;

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
	
	private final ResponseService responseService;
	
	@ApiOperation(value="TODO 리스트 조회", notes="모든 TODO를 조회한다.")
	@GetMapping()
	public ListResult<Todo> getTodos() {
		List<Todo> todos = todoService.getTodos();
		System.out.println(todos.get(0).getAccount());
		return responseService.getListResult(todoService.getTodos());
	}
	
	@ApiOperation(value="TODO 디테일 정보 조회", notes="todoId값을 이용하여 조회한다.")
	@GetMapping("{todoId}/")
	public String getTodo(@PathVariable Long todoId) {
		
		Todo todo = todoService.getTodo(todoId);
		TodoDetailDto todoDetailDto = new TodoDetailDto(todo);
		
		Account account = accountService.getAccount(todo.getAccount().getAccountId());
		AccountDto accountDto = new AccountDto(account);
		todoDetailDto.setAccount(accountDto);
		
		return todoDetailDto.toString();
	}
	
	
	@ApiOperation(value="TODO 추가", notes="TodoDTO타입을 이용하여 데이터를 받아온다.")
	@PostMapping()
	public Todo addTodo(@RequestBody TodoDto todoDto) {
		
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
	
	@ApiOperation(value="TODO 수정", notes="작성되어있는 TODO를 수정한다.")
	@PutMapping()
	public Todo modifyTodo(@RequestBody Todo todo) {
		return todoService.modifyTodo(todo);
	}
	
	@ApiOperation(value="TODO 삭제", notes="TODO를 삭제한다.")
	@DeleteMapping("{todoId}/")
	public void deleteTodo(@PathVariable Long todoId) {
		todoService.deleteTodo(todoId);
	}
	
}
