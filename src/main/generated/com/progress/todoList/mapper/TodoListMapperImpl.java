package com.progress.todoList.mapper;

import com.progress.todoList.dto.TodoItemDTO;
import com.progress.todoList.dto.TodoListDTO;
import com.progress.todoList.model.TodoItem;
import com.progress.todoList.model.TodoList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-09T22:50:23-0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class TodoListMapperImpl implements TodoListMapper {

    @Override
    public TodoList toModel(TodoListDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TodoList todoList = new TodoList();

        todoList.setName( dto.getName() );
        todoList.setDescription( dto.getDescription() );
        todoList.setTodoItemList( todoItemDTOSetToTodoItemSet( dto.getTodoItemList() ) );

        return todoList;
    }

    @Override
    public TodoListDTO toDTO(TodoList model) {
        if ( model == null ) {
            return null;
        }

        TodoListDTO todoListDTO = new TodoListDTO();

        todoListDTO.setName( model.getName() );
        todoListDTO.setDescription( model.getDescription() );
        todoListDTO.setTodoItemList( todoItemSetToTodoItemDTOSet( model.getTodoItemList() ) );

        return todoListDTO;
    }

    @Override
    public List<TodoList> toModelList(List<TodoListDTO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<TodoList> list = new ArrayList<TodoList>( dto.size() );
        for ( TodoListDTO todoListDTO : dto ) {
            list.add( toModel( todoListDTO ) );
        }

        return list;
    }

    @Override
    public List<TodoListDTO> toDTOList(List<TodoList> model) {
        if ( model == null ) {
            return null;
        }

        List<TodoListDTO> list = new ArrayList<TodoListDTO>( model.size() );
        for ( TodoList todoList : model ) {
            list.add( toDTO( todoList ) );
        }

        return list;
    }

    protected TodoItem todoItemDTOToTodoItem(TodoItemDTO todoItemDTO) {
        if ( todoItemDTO == null ) {
            return null;
        }

        TodoItem todoItem = new TodoItem();

        todoItem.setName( todoItemDTO.getName() );
        todoItem.setDetails( todoItemDTO.getDetails() );

        return todoItem;
    }

    protected Set<TodoItem> todoItemDTOSetToTodoItemSet(Set<TodoItemDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<TodoItem> set1 = new HashSet<TodoItem>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TodoItemDTO todoItemDTO : set ) {
            set1.add( todoItemDTOToTodoItem( todoItemDTO ) );
        }

        return set1;
    }

    protected TodoItemDTO todoItemToTodoItemDTO(TodoItem todoItem) {
        if ( todoItem == null ) {
            return null;
        }

        TodoItemDTO todoItemDTO = new TodoItemDTO();

        todoItemDTO.setName( todoItem.getName() );
        todoItemDTO.setDetails( todoItem.getDetails() );

        return todoItemDTO;
    }

    protected Set<TodoItemDTO> todoItemSetToTodoItemDTOSet(Set<TodoItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<TodoItemDTO> set1 = new HashSet<TodoItemDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TodoItem todoItem : set ) {
            set1.add( todoItemToTodoItemDTO( todoItem ) );
        }

        return set1;
    }
}
