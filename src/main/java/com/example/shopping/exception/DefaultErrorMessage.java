package com.example.shopping.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Accessors(chain = true)
@Data
@NoArgsConstructor
public class DefaultErrorMessage implements Serializable {
  private static final String MDC_SLEUTH_TRACE_ID = "traceId";
  private String errorMessage;
  private String traceId;

  public DefaultErrorMessage(HttpStatus status) {
    this(status, "");
  }

  public DefaultErrorMessage(HttpStatus status, Exception ex) {
    this(status, ex.getMessage());
  }

  public DefaultErrorMessage(HttpStatus status, String errorMessage) {
    this.errorMessage = String.format("[%s] %s", status.getReasonPhrase(), errorMessage);
    this.traceId = MDC.get(MDC_SLEUTH_TRACE_ID);
  }
}
