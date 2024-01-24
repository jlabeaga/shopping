package com.example.shopping.exception;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

  private HttpStatus status;

  public BusinessException(@NonNull HttpStatus status, String message) {
    super(message);
    this.status = status;
  }
}
