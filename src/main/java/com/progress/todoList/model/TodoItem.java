package com.progress.todoList.model;

import lombok.Data;

@Data
public class TodoItem {
    private int id;
    private String name;
    private String details;
    private int listId;
}
