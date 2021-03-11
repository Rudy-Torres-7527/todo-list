package com.progress.todoList.service.impl;

import com.progress.todoList.dto.TodoItemDTO;
import com.progress.todoList.dto.TodoListDTO;
import com.progress.todoList.exceptions.NoDataFoundException;
import com.progress.todoList.helper.ResultSetHelper;
import com.progress.todoList.mapper.TodoListMapper;
import com.progress.todoList.mapper.TodoItemMapper;
import com.progress.todoList.model.TodoItem;
import com.progress.todoList.model.TodoList;
import com.progress.todoList.repository.H2Repo;
import com.progress.todoList.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;

import static com.progress.todoList.constants.QueryConstants.*;

@AllArgsConstructor
@Service("basicTodoService")
public class BasicTodoService implements TodoService<TodoListDTO, TodoItemDTO> {

    private H2Repo h2Repo;
    private ResultSetHelper resultSetHelper;
    private TodoListMapper todoListMapper;
    private TodoItemMapper todoItemMapper;

    @Override
    public TodoListDTO getList(@NonNull String name) {
        return todoListMapper.toDTO(validateAndFetchList(name));
    }

    @Override
    public TodoListDTO createList(@NonNull String name, @NonNull String description) {
        String query = String.format(INSERT_TODO_LIST + " (Name, Description) VALUES ('%s', '%s')", name, description);
        return updateList(query, name);
    }

    @Override
    public TodoListDTO addItem(@NonNull String listName, @NonNull TodoItemDTO itemDTO) {
        TodoList list = validateAndFetchList(listName);
        TodoItem newItem = todoItemMapper.toModel(itemDTO);
        String query = String.format(INSERT_TODO_ITEM + " (Name, Details, ListID) VALUES ('%s', '%s', '%d')", newItem.getName(), newItem.getDetails(), list.getId());
        return updateList(query, listName);
    }

    @Override
    public TodoListDTO deleteItem(@NonNull String listName, @NonNull String itemName) {
        TodoList existingList = validateAndFetchList(listName);
        TodoItem existingItem = validateAndFetchItem(itemName, existingList.getId());
        String query = String.format(DELETE_TODO_ITEM, existingItem.getName(), existingList.getId());
        return updateList(query, listName);
    }

    private TodoList validateAndFetchList(String name) {
        String query = String.format(FETCH_TODO_LIST_MODEL, name);
        ResultSet rs = h2Repo.queryDB(query);
        return resultSetHelper.getTodoList(rs).orElseThrow(() -> new NoDataFoundException("There was no Todo_List in DB for provided Key (" + name + ")"));
    }

    private TodoItem validateAndFetchItem(String itemName, int listId) {
        String query = String.format(FETCH_TODO_ITEM + " WHERE NAME = '%s' AND LISTID = '%d'", itemName, listId);
        ResultSet rs = h2Repo.queryDB(query);
        return resultSetHelper.getTodoItem(rs).orElseThrow(() -> new NoDataFoundException("There was no Todo_Item in DB for provided Key (" + itemName + ")"));
    }

    private TodoListDTO updateList(String query, String listName) {
        h2Repo.updateDB(query);
        return todoListMapper.toDTO(validateAndFetchList(listName));
    }

}
