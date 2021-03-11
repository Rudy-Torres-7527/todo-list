package com.progress.todoList.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseListDTO {

    @ApiModelProperty(name = "name", value = "Name of TODO List")
    @NotBlank(message = "name must not be null or blank")
    private String name;

    @ApiModelProperty(name = "description", value = "Description of TODO List")
    @NotBlank(message = "description must not be null or blank")
    private String description;

}
