package com.progress.todoList.mapper;

import com.progress.todoList.dto.TodoItemDTO;
import com.progress.todoList.model.TodoItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-09T22:50:23-0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class TodoItemMapperImpl implements TodoItemMapper {

    @Override
    public TodoItem toModel(TodoItemDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TodoItem todoItem = new TodoItem();

        todoItem.setName( dto.getName() );
        todoItem.setDetails( dto.getDetails() );

        return todoItem;
    }

    @Override
    public TodoItemDTO toDTO(TodoItem model) {
        if ( model == null ) {
            return null;
        }

        TodoItemDTO todoItemDTO = new TodoItemDTO();

        todoItemDTO.setName( model.getName() );
        todoItemDTO.setDetails( model.getDetails() );

        return todoItemDTO;
    }

    @Override
    public List<TodoItem> toModelList(List<TodoItemDTO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<TodoItem> list = new ArrayList<TodoItem>( dto.size() );
        for ( TodoItemDTO todoItemDTO : dto ) {
            list.add( toModel( todoItemDTO ) );
        }

        return list;
    }

    @Override
    public List<TodoItemDTO> toDTOList(List<TodoItem> model) {
        if ( model == null ) {
            return null;
        }

        List<TodoItemDTO> list = new ArrayList<TodoItemDTO>( model.size() );
        for ( TodoItem todoItem : model ) {
            list.add( toDTO( todoItem ) );
        }

        return list;
    }
}
