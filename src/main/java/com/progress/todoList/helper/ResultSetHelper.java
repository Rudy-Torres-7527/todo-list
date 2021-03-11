package com.progress.todoList.helper;

import com.progress.todoList.model.*;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class ResultSetHelper {

    public Optional<TodoList> getTodoList(@NonNull ResultSet resultSet) {
        TodoList todoList = mapTodoList(resultSet);
        if (todoList.getId() == 0) {
            return Optional.empty();
        }
        return Optional.of(todoList);
    }

    public Optional<TodoItem> getTodoItem(@NonNull ResultSet resultSet) {
        TodoItem todoItem = mapTodoItem(resultSet);
        if (todoItem.getId() == 0) {
            return Optional.empty();
        }
        return Optional.of(todoItem);
    }

    public List<EnhancedTodoList> getAllEnhancedTodoLists(@NonNull ResultSet resultSet) {
        List<DBRow> allDBRows = mapDBRows(resultSet);
        List<EnhancedTodoList> lists = mapEnhancedTodoLists(allDBRows);
        return lists;
    }

    public Optional<EnhancedTodoItem> getEnhancedTodoItem(@NonNull ResultSet resultSet) {
        EnhancedTodoItem todoItem = new EnhancedTodoItem();
        try {
            while (resultSet.next()) {
                todoItem.setId(resultSet.getInt("ID"));
                todoItem.setName(resultSet.getString("NAME"));
                todoItem.setDetails(resultSet.getString("DETAILS"));
                Date date = resultSet.getDate("EFFECTIVE_DATE");
                if (date != null) {
                    todoItem.setEffectiveDate(new Date(date.getTime()).toLocalDate());
                }
                todoItem.setCompleted(resultSet.getBoolean("COMPLETED"));
                todoItem.setListId(resultSet.getInt("LISTID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (todoItem.getId() == 0) {
            return Optional.empty();
        }
        return Optional.of(todoItem);
    }

    private TodoList mapTodoList(ResultSet resultSet){
        TodoList todoList = new TodoList();
        Set<TodoItem> items = new HashSet<>();
        try {
            while (resultSet.next()) {
                todoList.setId(resultSet.getInt("ID"));
                todoList.setName(resultSet.getString("NAME"));
                todoList.setDescription(resultSet.getString("DESCRIPTION"));

                TodoItem todoItem = new TodoItem();
                todoItem.setName(resultSet.getString("ITEMNAME"));
                todoItem.setDetails(resultSet.getString("DETAILS"));
                items.add(todoItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        todoList.setTodoItemList(items);
        return todoList;
    }

    private TodoItem mapTodoItem(ResultSet resultSet){
        TodoItem todoItem = new TodoItem();
        try {
            while (resultSet.next()) {
                todoItem.setId(resultSet.getInt("ID"));
                todoItem.setName(resultSet.getString("NAME"));
                todoItem.setDetails(resultSet.getString("DETAILS"));
                todoItem.setListId(resultSet.getInt("LISTID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItem;
    }

    private List<EnhancedTodoList> mapEnhancedTodoLists(List<DBRow> rows){
        List<EnhancedTodoList> lists = new ArrayList<>();
        List<DBRow> rowsWithItems = new ArrayList<>();
        List<DBRow> rowsWithNoItems = new ArrayList<>();

        parseRows(rows, rowsWithItems, rowsWithNoItems);
        mapRowsWithNoItems(lists, rowsWithNoItems);
        mapRowsWithItems(lists, rowsWithItems);

        return lists;
    }

    private void parseRows(List<DBRow> allRows, List<DBRow> rowsWithItems, List<DBRow> rowsWithNoItems) {
        for (DBRow row : allRows) {
            if (row.getItemName() == null) {
                rowsWithNoItems.add(row);
            } else {
                rowsWithItems.add(row);
            }
        }
    }

    private void mapRowsWithNoItems(List<EnhancedTodoList> lists, List<DBRow> rowsWithNoItems) {
        List<EnhancedTodoList> mappedLists = rowsWithNoItems
                .stream()
                .map(this::mapEnhancedTodoList)
                .collect(Collectors.toList());
        lists.addAll(mappedLists);
    }

    private void mapRowsWithItems(List<EnhancedTodoList> lists, List<DBRow> rowsWithItems) {
        EnhancedTodoList list = null;
        String tempListName = null;

        for (DBRow row : rowsWithItems) {

            if (!row.getName().equals(tempListName)) {
                // IGNORE FIRST ITERATION
                if (tempListName != null) {
                    lists.add(list);
                }
                tempListName = row.getName();
                list = mapEnhancedTodoList(row);
            }

            Set<EnhancedTodoItem> items = list.getEnhancedTodoItems();
            EnhancedTodoItem item = mapEnhancedTodoItem(row);
            items.add(item);
            list.setEnhancedTodoItems(items);

        }

        lists.add(list);
    }

    private EnhancedTodoList mapEnhancedTodoList(DBRow dbData) {
        EnhancedTodoList list = new EnhancedTodoList();
        list.setId(dbData.getId());
        list.setName(dbData.getName());
        list.setDescription(dbData.getDescription());
        list.setEnhancedTodoItems(new HashSet<>());
        return list;
    }

    private EnhancedTodoItem mapEnhancedTodoItem(DBRow dbData) {
        EnhancedTodoItem item = new EnhancedTodoItem();
        item.setId(dbData.getItemId());
        item.setName(dbData.getItemName());
        item.setDetails(dbData.getItemDetails());
        item.setListId(dbData.getId());
        item.setEffectiveDate(dbData.getEffectiveDate());
        item.setCompleted(dbData.isCompleted());
        return item;
    }

    private List<DBRow> mapDBRows(ResultSet resultSet) {
        List<DBRow> rows = new ArrayList<>();

        try {
            while (resultSet.next()) {
                DBRow row = new DBRow();

                row.setId(resultSet.getInt("LISTID"));
                row.setName(resultSet.getString("NAME"));
                row.setDescription(resultSet.getString("DESCRIPTION"));
                row.setItemId(resultSet.getInt("ITEMID"));

                row.setItemName(resultSet.getString("ITEMNAME"));
                row.setItemDetails(resultSet.getString("ITEMDETAILS"));
                row.setCompleted(resultSet.getBoolean("COMPLETED"));

                Date date = resultSet.getDate("EFFECTIVE_DATE");
                if (date != null) {
                    row.setEffectiveDate(new Date(date.getTime()).toLocalDate());
                }

                rows.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows;
    }

}
