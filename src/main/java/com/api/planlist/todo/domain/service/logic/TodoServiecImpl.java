package com.api.planlist.todo.domain.service.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.planlist.todo.domain.Todo;
import com.api.planlist.todo.domain.service.TodoService;
import com.api.planlist.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TodoServiecImpl implements TodoService{
	
	private final TodoRepository todoRepository;
	
	@Override
	public List<Todo> getTodos() {
		List<Todo> todos = todoRepository.findAll();
		return todos;
	}

	@Override
	public Todo getTodo(Long todoId) {
		Todo todo = todoRepository.findById(todoId).orElseThrow();
		return todo;
	}

	@Override
	public Todo addTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	@Override
	public Todo modifyTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	@Override
	public void deleteTodo(Long todoId) {
		todoRepository.deleteById(todoId);
	}

}
