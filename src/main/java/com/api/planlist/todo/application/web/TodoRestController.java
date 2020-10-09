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

import com.api.planlist.todo.domain.Todo;
import com.api.planlist.todo.domain.service.TodoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class TodoRestController {
	
	private final TodoService todoService;
	
	@GetMapping("todos/")
	public List<Todo> getTodos() {
		return todoService.getTodos();
	}
	
	@GetMapping("todo/{todoId}/")
	public Todo getTodo(@PathVariable Long todoId) {
		return todoService.getTodo(todoId);
	}
	
	@PostMapping("todo/")
	public Todo addTodo(@RequestBody Todo todo) {
		return todoService.addTodo(todo);
	}
	
	@PutMapping("todo/")
	public Todo modifyTodo(@RequestBody Todo todo) {
		return todoService.modifyTodo(todo);
	}
	
	@DeleteMapping("todo/{todoId}/")
	public void deleteTodo(@PathVariable Long todoId) {
		todoService.deleteTodo(todoId);
	}
	
}
