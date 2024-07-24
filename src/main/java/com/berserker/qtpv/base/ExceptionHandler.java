package com.berserker.qtpv.base;

import com.berserker.qtpv.model.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
  @org.springframework.web.bind.annotation.ExceptionHandler()
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public CustomException todoException(Exception e) {
    return CustomException.builder().message(e.getMessage()).build();
  }
}
