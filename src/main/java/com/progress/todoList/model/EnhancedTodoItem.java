package com.progress.todoList.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class EnhancedTodoItem extends TodoItem{
    private LocalDate effectiveDate;
    private boolean completed;
}
