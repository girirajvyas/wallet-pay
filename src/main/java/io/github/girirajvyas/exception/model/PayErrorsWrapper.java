package io.github.girirajvyas.exception.model;

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
