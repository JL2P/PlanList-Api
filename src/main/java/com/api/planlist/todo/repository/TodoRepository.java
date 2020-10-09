package com.api.planlist.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.planlist.todo.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
