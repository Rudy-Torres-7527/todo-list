package com.progress.todoList.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class EnhancedTodoItemDTO extends TodoItemDTO {

    @ApiModelProperty(name = "effectiveDate", value = "Effective date of when task should be completed by")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate;

    @ApiModelProperty(name = "completed", value = "Indicates if task has been completed")
    private boolean completed;
}
