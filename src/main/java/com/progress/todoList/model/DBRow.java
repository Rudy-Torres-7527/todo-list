package com.progress.todoList.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DBRow {
    private int id;
    private String name;
    private String description;

    private int itemId;
    private String itemName;
    private String itemDetails;
    private LocalDate effectiveDate;
    private boolean completed;
}
