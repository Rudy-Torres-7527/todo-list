package com.progress.todoList.model;

import lombok.Data;

import java.util.Set;

@Data
public class TodoList {
    private int id;
    private String name;
    private String description;
    private Set<TodoItem> todoItemList;
}
