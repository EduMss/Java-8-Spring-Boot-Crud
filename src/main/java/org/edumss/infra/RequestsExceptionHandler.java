package org.edumss.infra;

import lombok.var;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RequestsExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(){
        ExceptionDTO response = new ExceptionDTO("Data not found with provided ID", 404);
        return ResponseEntity.badRequest().body(response);
    }
}
