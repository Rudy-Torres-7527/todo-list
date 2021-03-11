package com.progress.todoList.service;

import com.progress.todoList.dto.EnhancedTodoItemDTO;
import com.progress.todoList.dto.EnhancedTodoListDTO;
import com.progress.todoList.exceptions.NoDataFoundException;
import com.progress.todoList.helper.ResultSetHelper;
import com.progress.todoList.mapper.EnhancedTodoItemMapper;
import com.progress.todoList.mapper.EnhancedTodoListMapper;
import com.progress.todoList.model.EnhancedTodoItem;
import com.progress.todoList.model.EnhancedTodoList;
import com.progress.todoList.repository.H2Repo;
import com.progress.todoList.service.impl.EnhancedTodoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhancedTodoServiceTest {

    @InjectMocks
    private EnhancedTodoService enhancedTodoService;

    @Mock
    private H2Repo h2Repo;

    @Mock
    private ResultSetHelper resultSetHelper;

    @Mock
    private EnhancedTodoListMapper enhancedTodoListMapper;

    @Mock
    private EnhancedTodoItemMapper enhancedTodoItemMapper;

    @Mock
    private ResultSet resultSet;

    private EnhancedTodoList enhancedTodoList;
    private EnhancedTodoListDTO enhancedTodoListDTO;
    private EnhancedTodoItem enhancedTodoItem;
    private EnhancedTodoItemDTO enhancedTodoItemDTO;

    private List<EnhancedTodoList> enhancedTodoLists;



    @Before
    public void setUp() {

        enhancedTodoItem = new EnhancedTodoItem();
        enhancedTodoItem.setName("Item_Name");
        enhancedTodoItem.setDetails("Item_Details");

        enhancedTodoItemDTO = new EnhancedTodoItemDTO();
        enhancedTodoItemDTO.setName("Item_Name");
        enhancedTodoItemDTO.setDetails("Item_Details");

        enhancedTodoList = new EnhancedTodoList();
        enhancedTodoList.setName("List_Name");
        enhancedTodoList.setDescription("List_Description");
        enhancedTodoList.setEnhancedTodoItems(Collections.singleton(enhancedTodoItem));

        enhancedTodoListDTO = new EnhancedTodoListDTO();
        enhancedTodoListDTO.setName("List_Name");
        enhancedTodoListDTO.setDescription("List_Description");
        enhancedTodoListDTO.setEnhancedTodoItems(Collections.singleton(enhancedTodoItemDTO));

        enhancedTodoLists = new ArrayList<>();
        enhancedTodoLists.add(enhancedTodoList);

        when(h2Repo.queryDB(anyString())).thenReturn(resultSet);
        doNothing().when(h2Repo).updateDB(anyString());
        when(resultSetHelper.getAllEnhancedTodoLists(any(ResultSet.class))).thenReturn(enhancedTodoLists);
        when(resultSetHelper.getEnhancedTodoItem(any(ResultSet.class))).thenReturn(Optional.of(enhancedTodoItem));
        when(enhancedTodoListMapper.toDTO(any(EnhancedTodoList.class))).thenReturn(enhancedTodoListDTO);
        when(enhancedTodoItemMapper.toModel(any(EnhancedTodoItemDTO.class))).thenReturn(enhancedTodoItem);
    }

    @Test
    public void getList_validListName_successful() {
        String listName = "List_Name";
        enhancedTodoService.getList(listName);
        verify(h2Repo).queryDB(anyString());
    }

    @Test(expected = NoDataFoundException.class)
    public void getList_inValidListName_failure() {
        String listName = "Invalid_List_Name";
        enhancedTodoService.getList(listName);
    }

    @Test
    public void createList_validDTO_successful() {
        enhancedTodoService.createList("List_Name", "List_Description");
        verify(h2Repo).updateDB(anyString());
    }

    @Test
    public void addItem_validNameAndDTO_successful() {
        enhancedTodoService.addItem("List_Name", enhancedTodoItemDTO);
        verify(h2Repo).updateDB(anyString());
    }

    @Test(expected = NoDataFoundException.class)
    public void addItem_invalidListName_failure() {
        enhancedTodoService.addItem("Invalid_List_Name", enhancedTodoItemDTO);
        verify(h2Repo).updateDB(anyString());
    }

    @Test
    public void deleteItem_validListAndItemName_successful() {
        enhancedTodoService.deleteItem("List_Name", "Item_Name");
        verify(h2Repo).updateDB(anyString());
    }

    @Test(expected = NoDataFoundException.class)
    public void deleteItem_invalidItemName_failure() {
        when(resultSetHelper.getEnhancedTodoItem(any(ResultSet.class))).thenReturn(Optional.empty());
        enhancedTodoService.deleteItem("List_Name", "Invalid_Item_Name");
    }
}
