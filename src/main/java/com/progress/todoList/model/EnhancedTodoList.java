package com.progress.todoList.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=true)
public class EnhancedTodoList extends TodoList {
    private Set<EnhancedTodoItem> enhancedTodoItems;
}
