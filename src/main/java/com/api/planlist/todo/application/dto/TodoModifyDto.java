package com.api.planlist.todo.application.dto;

import com.api.planlist.todo.domain.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
 * 	생성 : 정승훈
 *	설명 : Todo수정을 위한 Dto
 *        TodoAddDto와 다른점은 시작일자를 변경하지 않는다.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TodoModifyDto {
    private String accountId;
    private String title;
    private String description;
    private String endTime;
    private String completed;
    private String openAt;

    public TodoModifyDto(Todo todo) {
        BeanUtils.copyProperties(todo, this);
    }

    public Todo toDomain() {
        Todo todo = new Todo();
        BeanUtils.copyProperties(this, todo);
        return todo;
    }
}
