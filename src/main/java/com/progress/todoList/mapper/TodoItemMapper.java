package com.progress.todoList.mapper;

import com.progress.todoList.dto.TodoItemDTO;
import com.progress.todoList.model.TodoItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoItemMapper extends BaseMapper<TodoItem, TodoItemDTO>{
}
