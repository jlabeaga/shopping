package com.example.shopping.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BusinessException {

  public UnauthorizedException(String message) {
    super(HttpStatus.UNAUTHORIZED, message);
  }
}
