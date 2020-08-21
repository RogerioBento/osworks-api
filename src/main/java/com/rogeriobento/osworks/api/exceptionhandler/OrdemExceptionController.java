package com.rogeriobento.osworks.api.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrdemExceptionController {

   @ExceptionHandler(value = OrdemNotFoundException.class)

   public ResponseEntity<Object> exception(OrdemNotFoundException exception) {
      return new ResponseEntity<>("Ordem não encontrada!", HttpStatus.NOT_FOUND);
   }
   
}