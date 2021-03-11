package com.progress.todoList.controller;

import com.progress.todoList.dto.EnhancedTodoItemDTO;
import com.progress.todoList.dto.EnhancedTodoListDTO;
import com.progress.todoList.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/enhanced/todo")
public class EnhancedTodoListController {

    private TodoService enhancedTodoService;

    @GetMapping(path = "/getList/{name}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EnhancedTodoListDTO> findTodoList(@PathVariable String name){
        return new ResponseEntity<>((EnhancedTodoListDTO) enhancedTodoService.getList(name), HttpStatus.OK);
    }

    @PostMapping(path = "/createList/{name}/{description}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EnhancedTodoListDTO> createTodoList(@PathVariable String name, @PathVariable String description) {
        return new ResponseEntity<>((EnhancedTodoListDTO) enhancedTodoService.createList(name, description), HttpStatus.CREATED);
    }

    @PutMapping(path = "/updateList/addItem/{name}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EnhancedTodoListDTO> addTodoItem(@PathVariable String name, @RequestBody @Valid EnhancedTodoItemDTO item) {
        return new ResponseEntity<>((EnhancedTodoListDTO) enhancedTodoService.addItem(name, item), HttpStatus.OK);
    }

    @DeleteMapping(path = "/updateList/deleteItem/{listName}/{itemName}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EnhancedTodoListDTO> deleteTodoItem(@PathVariable String listName, @PathVariable String itemName) {
        return new ResponseEntity<>((EnhancedTodoListDTO) enhancedTodoService.deleteItem(listName, itemName), HttpStatus.OK);
    }
}
