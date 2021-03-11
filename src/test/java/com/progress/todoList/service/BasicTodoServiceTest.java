package com.progress.todoList.service;

import com.progress.todoList.dto.TodoItemDTO;
import com.progress.todoList.dto.TodoListDTO;
import com.progress.todoList.exceptions.NoDataFoundException;
import com.progress.todoList.helper.ResultSetHelper;
import com.progress.todoList.mapper.TodoItemMapper;
import com.progress.todoList.mapper.TodoListMapper;
import com.progress.todoList.model.TodoItem;
import com.progress.todoList.model.TodoList;
import com.progress.todoList.repository.H2Repo;
import com.progress.todoList.service.impl.BasicTodoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasicTodoServiceTest {

    @InjectMocks
    private BasicTodoService basicTodoService;

    @Mock
    private H2Repo h2Repo;

    @Mock
    private ResultSetHelper resultSetHelper;

    @Mock
    private TodoListMapper todoListMapper;

    @Mock
    private TodoItemMapper todoItemMapper;

    @Mock
    private ResultSet resultSet;

    private TodoList todoList;
    private TodoListDTO todoListDTO;
    private TodoItem todoItem;
    private TodoItemDTO todoItemDTO;



    @Before
    public void setUp() {

        todoItem = new TodoItem();
        todoItem.setName("Item_Name");
        todoItem.setDetails("Item_Details");

        todoItemDTO = new TodoItemDTO();
        todoItemDTO.setName("Item_Name");
        todoItemDTO.setDetails("Item_Details");

        todoList = new TodoList();
        todoList.setName("List_Name");
        todoList.setDescription("List_Description");

        todoListDTO = new TodoListDTO();
        todoListDTO.setName("List_Name");
        todoListDTO.setDescription("List_Description");

        when(h2Repo.queryDB(anyString())).thenReturn(resultSet);
        doNothing().when(h2Repo).updateDB(anyString());
        when(resultSetHelper.getTodoList(any(ResultSet.class))).thenReturn(Optional.of(todoList));
        when(resultSetHelper.getTodoItem(any(ResultSet.class))).thenReturn(Optional.of(todoItem));
        when(todoListMapper.toDTO(any(TodoList.class))).thenReturn(todoListDTO);
        when(todoItemMapper.toModel(any(TodoItemDTO.class))).thenReturn(todoItem);
    }

    @Test
    public void getList_validListName_successful() {
        String listName = "List_Name";
        basicTodoService.getList(listName);
        verify(h2Repo).queryDB(anyString());
    }

    @Test(expected = NoDataFoundException.class)
    public void getList_inValidListName_failure() {
        when(resultSetHelper.getTodoList(any(ResultSet.class))).thenReturn(Optional.empty());
        String listName = "Invalid_List_Name";
        basicTodoService.getList(listName);
    }

    @Test
    public void createList_validDTO_successful() {
        basicTodoService.createList("List_Name", "List_Description");
        verify(h2Repo).updateDB(anyString());
    }

    @Test(expected = NoDataFoundException.class)
    public void createList_inValidListName_failure() {
        when(resultSetHelper.getTodoList(any(ResultSet.class))).thenReturn(Optional.empty());
        basicTodoService.createList("List_Name", "List_Description");
    }

    @Test
    public void addItem_validNameAndDTO_successful() {
        basicTodoService.addItem("List_Name", todoItemDTO);
        verify(h2Repo).updateDB(anyString());
    }

    @Test(expected = NoDataFoundException.class)
    public void addItem_invalidListName_failure() {
        when(resultSetHelper.getTodoList(any(ResultSet.class))).thenReturn(Optional.empty());
        basicTodoService.addItem("Invalid_List_Name", todoItemDTO);
        verify(h2Repo).updateDB(anyString());
    }

    @Test
    public void deleteItem_validListAndItemName_successful() {
        basicTodoService.deleteItem("List_Name", "Item_Name");
        verify(h2Repo).updateDB(anyString());
    }

    @Test(expected = NoDataFoundException.class)
    public void deleteItem_invalidItemName_failure() {
        when(resultSetHelper.getTodoItem(any(ResultSet.class))).thenReturn(Optional.empty());
        basicTodoService.deleteItem("List_Name", "Invalid_Item_Name");
    }
}
