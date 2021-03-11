package com.progress.todoList.controller;

import com.progress.todoList.dto.TodoItemDTO;
import com.progress.todoList.dto.TodoListDTO;
import com.progress.todoList.service.TodoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static com.progress.todoList.constants.UrlConstants.ADD_ITEM_URI;
import static com.progress.todoList.constants.UrlConstants.BASIC_CONTROLLER_BASE_URL;
import static com.progress.todoList.constants.UrlConstants.CREATE_LIST_URI;
import static com.progress.todoList.constants.UrlConstants.DELETE_ITEM_URI;
import static com.progress.todoList.constants.UrlConstants.GET_LIST_URI;
import static com.progress.todoList.utils.Utility.getObjectMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TodoListControllerTest {

    @InjectMocks
    private TodoListController todoListController;

    @Mock
    private TodoService basicTodoService;

    private MockMvc mockMvc;
    private TodoListDTO todoListDTO;
    private TodoItemDTO todoItemDTO;

    @Before
    public void setUp() {
        todoListDTO = new TodoListDTO();
        todoListDTO.setName("List_Name");
        todoListDTO.setDescription("List_Description");

        mockMvc = MockMvcBuilders.standaloneSetup(todoListController).build();

        when(basicTodoService.createList(anyString(), anyString())).thenReturn(todoListDTO);
        when(basicTodoService.deleteItem(anyString(), anyString())).thenReturn(todoListDTO);

    }

    @Test
    public void getTodoList_validPayload_successfulResponse() throws Exception {

        String listName = "List_Name";

        mockMvc.perform(get(BASIC_CONTROLLER_BASE_URL + GET_LIST_URI, listName)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(basicTodoService).getList(listName);

    }

    @Test
    public void createTodoList_validPayload_successfulResponse() throws Exception {

        mockMvc.perform(post(BASIC_CONTROLLER_BASE_URL + CREATE_LIST_URI, "List_Name", "List_Description")
                .content(getObjectMapper().writeValueAsString(todoListDTO))
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

        verify(basicTodoService).createList(anyString(), anyString());

    }

    @Test
    public void addTodoItem_validPayload_successfulResponse() throws Exception {

        String listName = "List_Name";

        todoItemDTO = new TodoItemDTO();
        todoItemDTO.setName("Item_Name");
        todoItemDTO.setDetails("Item_Details");

        todoListDTO.setTodoItemList(Collections.singleton(todoItemDTO));

        when(basicTodoService.addItem(anyString(), any(TodoItemDTO.class))).thenReturn(todoListDTO);

        mockMvc.perform(put(BASIC_CONTROLLER_BASE_URL + ADD_ITEM_URI, listName)
                .content(getObjectMapper().writeValueAsString(todoItemDTO))
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(basicTodoService).addItem(listName, todoItemDTO);

    }

    @Test
    public void deleteTodoItem_validPayload_successfulResponse() throws Exception {

        String listName = "List_Name";
        String itemName = "Item_Name";

        mockMvc.perform(delete(BASIC_CONTROLLER_BASE_URL + DELETE_ITEM_URI, listName, itemName)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(basicTodoService).deleteItem(listName, itemName);

    }
}
