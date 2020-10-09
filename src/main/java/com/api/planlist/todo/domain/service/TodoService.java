package com.api.planlist.todo.domain.service;

import java.util.List;

import com.api.planlist.todo.domain.Todo;

public interface TodoService {
	public List<Todo> getTodos();
	public Todo getTodo(Long todoId);
	public Todo addTodo(Todo todo);
	public Todo modifyTodo(Todo todo);
	public void deleteTodo(Long todoId);
	
}
