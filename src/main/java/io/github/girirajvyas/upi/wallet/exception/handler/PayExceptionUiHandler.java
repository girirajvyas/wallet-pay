package io.github.girirajvyas.upi.wallet.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import io.github.girirajvyas.upi.wallet.exception.PayException;

@ControllerAdvice
public class PayExceptionUiHandler extends ResponseEntityExceptionHandler {

  public String handlePayException(PayException exception) {
    return "";
  }

}
