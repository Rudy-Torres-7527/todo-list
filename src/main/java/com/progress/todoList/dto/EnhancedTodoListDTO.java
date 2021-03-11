package com.progress.todoList.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class EnhancedTodoListDTO extends BaseListDTO {

    @ApiModelProperty(name = "list", value = "Items in the TODO List")
    private Set<EnhancedTodoItemDTO> enhancedTodoItems;
}
