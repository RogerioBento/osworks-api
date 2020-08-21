package com.rogeriobento.osworks.api.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClienteExceptionController {
   @ExceptionHandler(value = ClienteNotFoundException.class)
   public ResponseEntity<Object> exception(ClienteNotFoundException exception) {
      return new ResponseEntity<>("Cliente não encontrado!", HttpStatus.NOT_FOUND);
   }
}