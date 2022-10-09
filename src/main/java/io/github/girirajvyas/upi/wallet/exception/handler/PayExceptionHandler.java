package io.github.girirajvyas.upi.wallet.exception.handler;

import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import io.github.girirajvyas.upi.wallet.exception.PayException;
import io.github.girirajvyas.upi.wallet.exception.PayValidationException;
import io.github.girirajvyas.upi.wallet.exception.model.PayError;
import io.github.girirajvyas.upi.wallet.exception.model.PayErrors;
import io.github.girirajvyas.upi.wallet.exception.model.PayErrorsWrapper;

@ControllerAdvice
public class PayExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = PayException.class )
  public ResponseEntity<Object> handlePayException(PayException exception) {
    PayError payError = PayError.builder().details(exception.getMessage()).build();
    PayErrors payErrors = PayErrors.builder().errors(Arrays.asList(payError)).build();
    PayErrorsWrapper errorsWrapper = PayErrorsWrapper.builder().errors(payErrors).build();

    return new ResponseEntity<>(errorsWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @ExceptionHandler(value = PayValidationException.class )
  public ResponseEntity<Object> handlePayException(PayValidationException exception) {
    PayError payError = PayError.builder().details(exception.getMessage()).build();
    PayErrors payErrors = PayErrors.builder().errors(Arrays.asList(payError)).build();
    PayErrorsWrapper errorsWrapper = PayErrorsWrapper.builder().errors(payErrors).build();

    return new ResponseEntity<>(errorsWrapper, HttpStatus.BAD_REQUEST);
  }

}
