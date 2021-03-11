package com.progress.todoList.mapper;

import com.progress.todoList.dto.TodoListDTO;
import com.progress.todoList.model.TodoList;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoListMapper extends BaseMapper<TodoList, TodoListDTO>{
}
