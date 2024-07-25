package com.berserker.qtpv.base;

import com.berserker.qtpv.model.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler()
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public CustomException todoException(Exception e) {
    return CustomException.builder().message(e.getMessage()).build();
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public CustomException runtimeException(RuntimeException e) {
    return CustomException.builder().message(e.getMessage()).build();
  }
}

