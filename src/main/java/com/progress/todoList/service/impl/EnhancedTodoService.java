package com.progress.todoList.service.impl;

import com.progress.todoList.dto.EnhancedTodoItemDTO;
import com.progress.todoList.dto.EnhancedTodoListDTO;
import com.progress.todoList.exceptions.NoDataFoundException;
import com.progress.todoList.helper.ResultSetHelper;
import com.progress.todoList.mapper.EnhancedTodoItemMapper;
import com.progress.todoList.mapper.EnhancedTodoListMapper;
import com.progress.todoList.model.EnhancedTodoItem;
import com.progress.todoList.model.EnhancedTodoList;
import com.progress.todoList.repository.H2Repo;
import com.progress.todoList.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

import static com.progress.todoList.constants.QueryConstants.DELETE_TODO_ITEM;
import static com.progress.todoList.constants.QueryConstants.FETCH_ALL_ENHANCED_TODO_LISTS;
import static com.progress.todoList.constants.QueryConstants.FETCH_TODO_ITEM;
import static com.progress.todoList.constants.QueryConstants.INSERT_TODO_ITEM;
import static com.progress.todoList.constants.QueryConstants.INSERT_TODO_LIST;

@AllArgsConstructor
@Service("enhancedTodoService")
public class EnhancedTodoService implements TodoService<EnhancedTodoListDTO, EnhancedTodoItemDTO> {

    private H2Repo h2Repo;
    private ResultSetHelper resultSetHelper;
    private EnhancedTodoItemMapper enhancedTodoItemMapper;
    private EnhancedTodoListMapper enhancedTodoListMapper;

    @Override
    public EnhancedTodoListDTO getList(String name) {
        return enhancedTodoListMapper.toDTO(fetchList(name));
    }

    @Override
    public EnhancedTodoListDTO createList(@NonNull String name, @NonNull String description) {
        String query = String.format(INSERT_TODO_LIST + " (Name, Description) VALUES ('%s', '%s')", name, description);
        return updateList(query, name);
    }

    @Override
    public EnhancedTodoListDTO addItem(@NonNull String name, @NonNull EnhancedTodoItemDTO itemDTO) {
        EnhancedTodoList existingList = fetchList(name);
        EnhancedTodoItem newItem = enhancedTodoItemMapper.toModel(itemDTO);
        String query = String.format(INSERT_TODO_ITEM + " (Name, Details, Effective_Date, Completed, ListID) VALUES ('%s', '%s', '%s', '%b', '%d')", newItem.getName(), newItem.getDetails(), newItem.getEffectiveDate() != null ? newItem.getEffectiveDate().toString() : null, newItem.isCompleted(), existingList.getId());
        return updateList(query, existingList.getName());
    }

    @Override
    public EnhancedTodoListDTO deleteItem(@NonNull String listName, @NonNull String itemName) {
        EnhancedTodoList existingList = fetchList(listName);
        EnhancedTodoItem existingItem = fetchItem(itemName, existingList.getId());
        String query = String.format(DELETE_TODO_ITEM, existingItem.getName(), existingList.getId());
        return updateList(query, listName);
    }

    private List<EnhancedTodoList> fetchAllLists() {
        ResultSet rs = h2Repo.queryDB(FETCH_ALL_ENHANCED_TODO_LISTS);
        return resultSetHelper.getAllEnhancedTodoLists(rs);
    }

    private EnhancedTodoList fetchList(String listName) {
        List<EnhancedTodoList> existingLists = fetchAllLists();
        return getList(existingLists, listName);
    }

    private EnhancedTodoList getList(List<EnhancedTodoList> lists, String listName) {
        return lists
                .stream()
                .filter(list -> list.getName().equals(listName))
                .findFirst()
                .orElseThrow(() -> new NoDataFoundException("There was no Todo_List in DB for provided Key (" + listName + ")"));
    }

    private EnhancedTodoItem fetchItem(String itemName, int listId) {
        String query = String.format(FETCH_TODO_ITEM + " WHERE NAME = '%s' AND LISTID = '%d'", itemName, listId);
        ResultSet rs = h2Repo.queryDB(query);
        return resultSetHelper.getEnhancedTodoItem(rs).orElseThrow(() -> new NoDataFoundException("There was no Todo_Item in DB for provided Key (" + itemName + ")"));
    }

    private EnhancedTodoListDTO updateList(String query, String listName) {
        h2Repo.updateDB(query);
        return enhancedTodoListMapper.toDTO(fetchList(listName));
    }
}
