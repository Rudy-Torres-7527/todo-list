package com.progress.todoList.service;

import com.progress.todoList.dto.BaseListDTO;
import com.progress.todoList.dto.TodoItemDTO;

public interface TodoService<T extends BaseListDTO, S extends TodoItemDTO> {
    T getList(String name);
    T createList(String name, String description);
    T addItem(String name, S newItem);
    T deleteItem(String listName, String itemName);
}
