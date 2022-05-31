package com.wethinkcode.buyorsell.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.wethinkcode.buyorsell.exceptions.UserException;

@ControllerAdvice
public class UserExceptionController {

    @ExceptionHandler(value = UserException.class)
   public ResponseEntity<Object> exception(UserException exception) {
      return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
   }
}
