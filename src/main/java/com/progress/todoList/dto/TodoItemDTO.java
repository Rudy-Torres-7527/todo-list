package com.progress.todoList.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemDTO {

    @NotBlank(message = "name must not be null or blank")
    @ApiModelProperty(name = "name", value = "Name of TODO Item")
    private String name;

    @NotBlank(message = "details must not be null or blank")
    @ApiModelProperty(name = "description", value = "Details of TODO Item")
    private String details;

}
