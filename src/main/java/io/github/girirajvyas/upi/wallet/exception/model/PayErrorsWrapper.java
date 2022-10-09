package io.github.girirajvyas.upi.wallet.exception.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PayErrorsWrapper {

  @JsonProperty("Errors")
  private PayErrors errors;
  
}
