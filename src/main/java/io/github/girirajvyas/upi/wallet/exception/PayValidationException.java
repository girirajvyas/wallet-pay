package io.github.girirajvyas.upi.wallet.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayValidationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public PayValidationException() { }

  public PayValidationException(String message) {
    super(message);
  }

  public PayValidationException(Throwable cause) {
    super(cause);
  }
  
  public PayValidationException(String message, Throwable cause) {
    super(message, cause);
  }

}
