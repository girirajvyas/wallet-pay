package io.github.girirajvyas.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public PayException() { }

  public PayException(String message) {
    super(message);
  }

  public PayException(Throwable cause) {
    super(cause);
  }
  
  public PayException(String message, Throwable cause) {
    super(message, cause);
  }

}
