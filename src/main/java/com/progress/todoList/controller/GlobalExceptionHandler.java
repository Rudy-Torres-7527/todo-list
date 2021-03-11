package com.progress.todoList.controller;

import com.progress.todoList.exceptions.NoDataFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        return new ResponseEntity<>(getResponseBody(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), status.value(), errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleMissingFieldValue(NullPointerException nullPointerException){
        List<String> errors = Arrays.asList(nullPointerException.getMessage());
        return new ResponseEntity(getResponseBody(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), 400, errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity handleInvalidQueryKey(NoDataFoundException noDataFoundException){
        List<String> errors = Arrays.asList(noDataFoundException.getMessage());
        return new ResponseEntity(getResponseBody(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), 404, errors), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleInvalidUpdateKey(DuplicateKeyException duplicateKeyException){
        List<String> errors = Arrays.asList(duplicateKeyException.getMessage());
        return new ResponseEntity(getResponseBody(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), 400, errors), HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> getResponseBody(String timeStamp, int status, List<String> errors){
        Map<String, Object> body = new LinkedHashMap();
        body.put("timestamp", timeStamp);
        body.put("status", status);
        body.put("errors", errors);
        return body;
    }
}
