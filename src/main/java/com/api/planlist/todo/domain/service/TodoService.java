package com.api.planlist.todo.domain.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.api.planlist.common.model.exception.CommonCustomException;
import com.api.planlist.todo.domain.Todo;

public interface TodoService {
	public List<Todo> getTodos() throws NoSuchElementException;
	public Todo getTodo(Long todoId) throws NoSuchElementException;
	public Todo addTodo(Todo todo) throws CommonCustomException;
	public Todo modifyTodo(Todo todo) throws NoSuchElementException;
	public void deleteTodo(Long todoId) throws NoSuchElementException;
	public boolean isExist(Long todoId);
	
}
