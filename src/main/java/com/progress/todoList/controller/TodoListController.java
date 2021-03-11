package com.progress.todoList.controller;

import com.progress.todoList.dto.TodoItemDTO;
import com.progress.todoList.dto.TodoListDTO;
import com.progress.todoList.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/todo")
public class TodoListController {

    private TodoService basicTodoService;

    @GetMapping(path = "/getList/{name}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoListDTO> findTodoList(@PathVariable String name) {
        return new ResponseEntity<>((TodoListDTO) basicTodoService.getList(name), HttpStatus.OK);
    }

    @PostMapping(path = "/createList/{name}/{description}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoListDTO> createTodoList(@PathVariable String name, @PathVariable String description) {
        return new ResponseEntity<>((TodoListDTO) basicTodoService.createList(name, description), HttpStatus.CREATED);
    }

    @PutMapping(path = "/updateList/addItem/{name}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoListDTO> addTodoItem(@PathVariable String name, @RequestBody @Valid TodoItemDTO item) {
        return new ResponseEntity<>((TodoListDTO) basicTodoService.addItem(name, item), HttpStatus.OK);
    }

    @DeleteMapping(path = "/updateList/deleteItem/{listName}/{itemName}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoListDTO> deleteTodoItem(@PathVariable String listName, @PathVariable String itemName) {
        return new ResponseEntity<>((TodoListDTO) basicTodoService.deleteItem(listName, itemName), HttpStatus.OK);
    }
}
