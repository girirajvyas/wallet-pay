package io.github.girirajvyas.upi.wallet.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayError {

  @JsonProperty("Source")
  private String source;
  
  @JsonProperty("ReasonCode")
  private String reasonCode;
  
  @JsonProperty("Description")
  private String description;
  
  @JsonProperty("Recoverable")
  private Boolean recoverable;
  
  @JsonProperty("Details")
  private String details;
  
}
