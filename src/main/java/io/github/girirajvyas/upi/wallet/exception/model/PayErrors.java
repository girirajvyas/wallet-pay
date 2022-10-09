package io.github.girirajvyas.upi.wallet.exception.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PayErrors {

  @JsonProperty("Error")
  List<PayError> errors = new ArrayList<>();
  
}
