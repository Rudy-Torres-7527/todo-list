package com.progress.todoList.mapper;

import com.progress.todoList.dto.EnhancedTodoListDTO;
import com.progress.todoList.model.EnhancedTodoList;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnhancedTodoListMapper extends BaseMapper<EnhancedTodoList, EnhancedTodoListDTO>{
}
