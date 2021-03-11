package com.progress.todoList.controller;

import com.progress.todoList.dto.EnhancedTodoItemDTO;
import com.progress.todoList.dto.EnhancedTodoListDTO;
import com.progress.todoList.service.TodoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;

import static com.progress.todoList.constants.UrlConstants.ADD_ITEM_URI;
import static com.progress.todoList.constants.UrlConstants.CREATE_LIST_URI;
import static com.progress.todoList.constants.UrlConstants.DELETE_ITEM_URI;
import static com.progress.todoList.constants.UrlConstants.ENHANCED_CONTROLLER_BASE_URL;
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
public class EnhancedTodoListControllerTest {

    @InjectMocks
    private EnhancedTodoListController enhancedTodoListController;

    @Mock
    private TodoService enhancedTodoService;

    private MockMvc mockMvc;
    private EnhancedTodoListDTO enhancedTodoListDTO;
    private EnhancedTodoItemDTO enhancedTodoItemDTO;

    @Before
    public void setUp() {
        enhancedTodoListDTO = new EnhancedTodoListDTO();
        enhancedTodoListDTO.setName("List_Name");
        enhancedTodoListDTO.setDescription("List_Description");

        mockMvc = MockMvcBuilders.standaloneSetup(enhancedTodoListController).build();

        when(enhancedTodoService.createList(anyString(), anyString())).thenReturn(enhancedTodoListDTO);
        when(enhancedTodoService.deleteItem(anyString(), anyString())).thenReturn(enhancedTodoListDTO);

    }

    @Test
    public void getTodoList_validPayload_successfulResponse() throws Exception {

        String listName = "List_Name";

        mockMvc.perform(get(ENHANCED_CONTROLLER_BASE_URL + GET_LIST_URI, listName)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(enhancedTodoService).getList(listName);

    }

    @Test
    public void createTodoList_validPayload_successfulResponse() throws Exception {

        mockMvc.perform(post(ENHANCED_CONTROLLER_BASE_URL + CREATE_LIST_URI, "List_Name", "List_Description")
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

        verify(enhancedTodoService).createList(anyString(), anyString());

    }

    @Test
    public void addTodoItem_validPayload_successfulResponse() throws Exception {

        String listName = "List_Name";

        enhancedTodoItemDTO = new EnhancedTodoItemDTO();
        enhancedTodoItemDTO.setName("Item_Name");
        enhancedTodoItemDTO.setDetails("Item_Details");
        enhancedTodoItemDTO.setCompleted(true);
        enhancedTodoItemDTO.setEffectiveDate(LocalDate.of(2020, 10, 10));

        enhancedTodoListDTO.setEnhancedTodoItems(Collections.singleton(enhancedTodoItemDTO));

        when(enhancedTodoService.addItem(anyString(), any(EnhancedTodoItemDTO.class))).thenReturn(enhancedTodoListDTO);

        mockMvc.perform(put(ENHANCED_CONTROLLER_BASE_URL + ADD_ITEM_URI, listName)
                .content(getObjectMapper().writeValueAsString(enhancedTodoItemDTO))
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(enhancedTodoService).addItem(listName, enhancedTodoItemDTO);

    }

    @Test
    public void deleteTodoItem_validPayload_successfulResponse() throws Exception {

        String listName = "List_Name";
        String itemName = "Item_Name";

        mockMvc.perform(delete(ENHANCED_CONTROLLER_BASE_URL + DELETE_ITEM_URI, listName, itemName)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(enhancedTodoService).deleteItem(listName, itemName);

    }
}
