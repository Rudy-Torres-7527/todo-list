package com.progress.todoList.mapper;

import com.progress.todoList.dto.EnhancedTodoItemDTO;
import com.progress.todoList.model.EnhancedTodoItem;
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
public class EnhancedTodoItemMapperImpl implements EnhancedTodoItemMapper {

    @Override
    public EnhancedTodoItem toModel(EnhancedTodoItemDTO dto) {
        if ( dto == null ) {
            return null;
        }

        EnhancedTodoItem enhancedTodoItem = new EnhancedTodoItem();

        enhancedTodoItem.setName( dto.getName() );
        enhancedTodoItem.setDetails( dto.getDetails() );
        enhancedTodoItem.setEffectiveDate( dto.getEffectiveDate() );
        enhancedTodoItem.setCompleted( dto.isCompleted() );

        return enhancedTodoItem;
    }

    @Override
    public EnhancedTodoItemDTO toDTO(EnhancedTodoItem model) {
        if ( model == null ) {
            return null;
        }

        EnhancedTodoItemDTO enhancedTodoItemDTO = new EnhancedTodoItemDTO();

        enhancedTodoItemDTO.setName( model.getName() );
        enhancedTodoItemDTO.setDetails( model.getDetails() );
        enhancedTodoItemDTO.setEffectiveDate( model.getEffectiveDate() );
        enhancedTodoItemDTO.setCompleted( model.isCompleted() );

        return enhancedTodoItemDTO;
    }

    @Override
    public List<EnhancedTodoItem> toModelList(List<EnhancedTodoItemDTO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<EnhancedTodoItem> list = new ArrayList<EnhancedTodoItem>( dto.size() );
        for ( EnhancedTodoItemDTO enhancedTodoItemDTO : dto ) {
            list.add( toModel( enhancedTodoItemDTO ) );
        }

        return list;
    }

    @Override
    public List<EnhancedTodoItemDTO> toDTOList(List<EnhancedTodoItem> model) {
        if ( model == null ) {
            return null;
        }

        List<EnhancedTodoItemDTO> list = new ArrayList<EnhancedTodoItemDTO>( model.size() );
        for ( EnhancedTodoItem enhancedTodoItem : model ) {
            list.add( toDTO( enhancedTodoItem ) );
        }

        return list;
    }
}
