package com.example.shopping.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Enumeration;

//@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<DefaultErrorMessage> generalException(
    Exception e, HttpServletRequest request) {
    logException(e, request);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
      // unhandled exception messages shouldn't get outside
      .body(new DefaultErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR));
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<DefaultErrorMessage> businessException(
    BusinessException e, HttpServletRequest request) {
    logException(e, request);

    DefaultErrorMessage body;
    if (e.getStatus().is5xxServerError()) {
      // error messages for 5XX errors should not arrive at the client
      body = new DefaultErrorMessage(e.getStatus());
    } else {
      body = new DefaultErrorMessage(e.getStatus(), e);
    }
    return ResponseEntity.status(e.getStatus()).body(body);
  }

  private void logException(Exception ex, HttpServletRequest request) {
    if (ex == null) {
      return;
    }

    if (BusinessException.class.isAssignableFrom(ex.getClass())) {
      if (((BusinessException) ex).getStatus().is5xxServerError()) {
        // error messages for 5XX errors should still be logged as errors
        if (request != null) {
          log.error("curl to reproduce the error: " + requestToCurl(request));
        }
        String message = "Unhandled-Exception";
        log.error(message, ex);
      } else {
        if (request != null) {
          log.info("curl to reproduce the error: " + requestToCurl(request));
        }
        String message = "Handled-Exception";
        log.info(message, ex);
      }
    } else {
      if (request != null) {
        log.error("curl to reproduce the error: " + requestToCurl(request));
      }
      String message = "Unhandled-Exception";
      log.error(message, ex);
    }
  }

  // based on:
  // https://medium.com/@alaajawhar123/generate-curl-from-httprequest-in-spring-b85d8567360d
  @SneakyThrows
  private String requestToCurl(HttpServletRequest request) {

    StringBuilder result = new StringBuilder();

    result.append("curl --location --request ");

    // output method
    result.append(request.getMethod()).append(" ");

    // output url
    result.append("\"").append(request.getRequestURL().toString()).append("\"");

    // output headers
    for (Enumeration<String> headerNames = request.getHeaderNames();
         headerNames.hasMoreElements(); ) {
      String headerName = headerNames.nextElement();
      result
        .append(" -H \"")
        .append(headerName)
        .append(": ")
        .append(request.getHeader(headerName))
        .append("\"");
    }

    // output parameters
    for (Enumeration<String> parameterNames = request.getParameterNames();
         parameterNames.hasMoreElements(); ) {
      String parameterName = parameterNames.nextElement();
      result
        .append(" -d \"")
        .append(parameterName)
        .append("=")
        .append(request.getParameter(parameterName))
        .append("\"");
    }

    // output body
    if (RequestMethod.POST.name().equalsIgnoreCase(request.getMethod())) {
      String body = readBody(request);
      if (body.length() > 0) {
        result.append(" -d '").append(body).append("'");
      }
    }

    return result.toString();
  }

  @SneakyThrows
  private String readBody(HttpServletRequest request) {
    return request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
  }
}
