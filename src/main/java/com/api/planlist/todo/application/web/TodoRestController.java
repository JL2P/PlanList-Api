package com.api.planlist.todo.application.web;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.api.planlist.common.model.ErrorMessage;
import com.api.planlist.common.model.exception.CommonCustomException;
import com.api.planlist.todo.application.dto.TodoModifyDto;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import com.api.planlist.account.application.dto.AccountDto;
import com.api.planlist.account.domain.Account;
import com.api.planlist.account.domain.service.AccountService;
import com.api.planlist.common.model.response.CommonResult;
import com.api.planlist.common.model.response.ListResult;
import com.api.planlist.common.model.response.SingleResult;
import com.api.planlist.common.model.service.ResponseService;
import com.api.planlist.todo.application.dto.TodoAddDto;
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
	public List<TodoDto> getTodos() throws NoSuchElementException {
		List<Todo> todos = todoService.getTodos();
		// 가지고온 TodoList를 TodoDto리스트로 변환한다.
		return todos.stream().map(todo->new TodoDto(todo)).collect(Collectors.toList());
	}
	
	@ApiOperation(value="TODO 디테일 정보 조회", notes="todoId값을 이용하여 조회한다.")
	@GetMapping("{todoId}/")
	public TodoDetailDto getTodo(@PathVariable Long todoId)throws NoSuchElementException {
		//Todo 객체를 가지고온다
		Todo todo = todoService.getTodo(todoId);
		//Todo 객체를 TodoDto객체로 변환한다.
		TodoDetailDto todoDetailDto = new TodoDetailDto(todo);
		//변환된 TodoDto객체에 Account객체 정보를 AccountDto형태로 넣어준다
		Account account = accountService.getAccount(todo.getAccount().getAccountId());
		AccountDto accountDto = new AccountDto(account);
		todoDetailDto.setAccount(accountDto);
		
		return todoDetailDto;
		
	}

	@ApiOperation(value="TODO 추가", notes="TodoDTO타입을 이용하여 데이터를 받아온다.")
	@PostMapping()
	public TodoAddDto addTodo(@RequestBody TodoAddDto todoAddDto) throws CommonCustomException {
		//todoDto를 받아 todo 객체로 변환한다.
		Todo newTodo = todoAddDto.toDomain();
		//todo를 저장하려면 Account객체가 필요하다
		//todo객체에 Dto로 받아온 accountId를 이용하여 Account객체를 받아 넣어준다
		newTodo.setAccount(accountService.getAccount(todoAddDto.getAccountId()));
		//todo객체를 추가한다. 추가후 반환되는 데이터는 TodoAddDto형태로 변환하여 보내준다.
		return new TodoAddDto(todoService.addTodo(newTodo));
	}	
	
	@ApiOperation(value="TODO 수정", notes="작성되어있는 TODO를 수정한다.")
	@PutMapping()
	public TodoDto modifyTodo(@RequestBody TodoModifyDto todoModifyDto)throws NoSuchElementException {
		//Todo객체로 변환한다.
		Todo todo = todoModifyDto.toDomain();
		 return new TodoDto(todoService.modifyTodo(todo));
	}
	
	@ApiOperation(value="TODO 삭제", notes="TODO를 삭제한다.")
	@DeleteMapping("{todoId}/")
	public String deleteTodo(@PathVariable Long todoId) throws NoSuchElementException{
		todoService.deleteTodo(todoId);
		return "success";
	}

	@ExceptionHandler(RuntimeException.class)
	public @ResponseBody ErrorMessage runTimeError(RuntimeException e) throws NoSuchElementException{
		ErrorMessage error = new ErrorMessage();
		error.setMessage(e.getMessage());
		return error;
	}
}