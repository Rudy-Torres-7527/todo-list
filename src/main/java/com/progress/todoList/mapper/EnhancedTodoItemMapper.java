package com.progress.todoList.mapper;

import com.progress.todoList.dto.EnhancedTodoItemDTO;
import com.progress.todoList.model.EnhancedTodoItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnhancedTodoItemMapper extends BaseMapper<EnhancedTodoItem, EnhancedTodoItemDTO>{
}
