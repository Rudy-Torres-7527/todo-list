package com.progress.todoList.mapper;

import com.progress.todoList.dto.EnhancedTodoItemDTO;
import com.progress.todoList.dto.EnhancedTodoListDTO;
import com.progress.todoList.model.EnhancedTodoItem;
import com.progress.todoList.model.EnhancedTodoList;
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
public class EnhancedTodoListMapperImpl implements EnhancedTodoListMapper {

    @Override
    public EnhancedTodoList toModel(EnhancedTodoListDTO dto) {
        if ( dto == null ) {
            return null;
        }

        EnhancedTodoList enhancedTodoList = new EnhancedTodoList();

        enhancedTodoList.setName( dto.getName() );
        enhancedTodoList.setDescription( dto.getDescription() );
        enhancedTodoList.setEnhancedTodoItems( enhancedTodoItemDTOSetToEnhancedTodoItemSet( dto.getEnhancedTodoItems() ) );

        return enhancedTodoList;
    }

    @Override
    public EnhancedTodoListDTO toDTO(EnhancedTodoList model) {
        if ( model == null ) {
            return null;
        }

        EnhancedTodoListDTO enhancedTodoListDTO = new EnhancedTodoListDTO();

        enhancedTodoListDTO.setName( model.getName() );
        enhancedTodoListDTO.setDescription( model.getDescription() );
        enhancedTodoListDTO.setEnhancedTodoItems( enhancedTodoItemSetToEnhancedTodoItemDTOSet( model.getEnhancedTodoItems() ) );

        return enhancedTodoListDTO;
    }

    @Override
    public List<EnhancedTodoList> toModelList(List<EnhancedTodoListDTO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<EnhancedTodoList> list = new ArrayList<EnhancedTodoList>( dto.size() );
        for ( EnhancedTodoListDTO enhancedTodoListDTO : dto ) {
            list.add( toModel( enhancedTodoListDTO ) );
        }

        return list;
    }

    @Override
    public List<EnhancedTodoListDTO> toDTOList(List<EnhancedTodoList> model) {
        if ( model == null ) {
            return null;
        }

        List<EnhancedTodoListDTO> list = new ArrayList<EnhancedTodoListDTO>( model.size() );
        for ( EnhancedTodoList enhancedTodoList : model ) {
            list.add( toDTO( enhancedTodoList ) );
        }

        return list;
    }

    protected EnhancedTodoItem enhancedTodoItemDTOToEnhancedTodoItem(EnhancedTodoItemDTO enhancedTodoItemDTO) {
        if ( enhancedTodoItemDTO == null ) {
            return null;
        }

        EnhancedTodoItem enhancedTodoItem = new EnhancedTodoItem();

        enhancedTodoItem.setName( enhancedTodoItemDTO.getName() );
        enhancedTodoItem.setDetails( enhancedTodoItemDTO.getDetails() );
        enhancedTodoItem.setEffectiveDate( enhancedTodoItemDTO.getEffectiveDate() );
        enhancedTodoItem.setCompleted( enhancedTodoItemDTO.isCompleted() );

        return enhancedTodoItem;
    }

    protected Set<EnhancedTodoItem> enhancedTodoItemDTOSetToEnhancedTodoItemSet(Set<EnhancedTodoItemDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<EnhancedTodoItem> set1 = new HashSet<EnhancedTodoItem>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( EnhancedTodoItemDTO enhancedTodoItemDTO : set ) {
            set1.add( enhancedTodoItemDTOToEnhancedTodoItem( enhancedTodoItemDTO ) );
        }

        return set1;
    }

    protected EnhancedTodoItemDTO enhancedTodoItemToEnhancedTodoItemDTO(EnhancedTodoItem enhancedTodoItem) {
        if ( enhancedTodoItem == null ) {
            return null;
        }

        EnhancedTodoItemDTO enhancedTodoItemDTO = new EnhancedTodoItemDTO();

        enhancedTodoItemDTO.setName( enhancedTodoItem.getName() );
        enhancedTodoItemDTO.setDetails( enhancedTodoItem.getDetails() );
        enhancedTodoItemDTO.setEffectiveDate( enhancedTodoItem.getEffectiveDate() );
        enhancedTodoItemDTO.setCompleted( enhancedTodoItem.isCompleted() );

        return enhancedTodoItemDTO;
    }

    protected Set<EnhancedTodoItemDTO> enhancedTodoItemSetToEnhancedTodoItemDTOSet(Set<EnhancedTodoItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<EnhancedTodoItemDTO> set1 = new HashSet<EnhancedTodoItemDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( EnhancedTodoItem enhancedTodoItem : set ) {
            set1.add( enhancedTodoItemToEnhancedTodoItemDTO( enhancedTodoItem ) );
        }

        return set1;
    }
}
