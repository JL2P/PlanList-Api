package com.api.planlist.todo.domain.service.logic;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.api.planlist.common.model.exception.CommonCustomException;
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
	public List<Todo> getTodos() throws NoSuchElementException{
		List<Todo> todos = todoRepository.findAll();
		return todos;
	}

	@Override
	public Todo getTodo(Long todoId) throws NoSuchElementException{
		//데이터가 하나도 없을 경우 빈 Todo객체 반환
		if(!isExist(todoId)) return new Todo();

		return todoRepository.findById(todoId).orElseThrow(()-> new NoSuchElementException());

	}

	@Override
	public Todo addTodo(Todo todo) throws CommonCustomException {
		if(isExist(todo.getId()))throw new CommonCustomException(todo.getId().toString());

		return todoRepository.save(todo);
	}

	@Override
	public Todo modifyTodo(Todo todo) throws NoSuchElementException {
		//DB에 todo가 존재하는지 확인
		if(!isExist(todo.getId()))throw new NoSuchElementException(todo.getId().toString());

		return todoRepository.save(todo);
	}

	@Override
	public void deleteTodo(Long todoId) throws NoSuchElementException {
		if(!isExist(todoId))throw new NoSuchElementException(todoId.toString());

		todoRepository.deleteById(todoId);
	}

	@Override
	public boolean isExist(Long todoId) {
		Optional<Todo> todoOpt = todoRepository.findById(todoId);
		//Optional안에 todo객체가 존재하는 경우
		if(todoOpt.isPresent()) return true;

		//Optional안에 todo객체가 존재하지 않는 경우
		return false;
	}

}
