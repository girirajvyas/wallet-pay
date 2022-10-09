package io.github.girirajvyas.upi.wallet.model;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pin {

  @Parameter(description = "Mobile Number", example = "9876543210")
  private Long mobileNumber;
  
  @Parameter(description = "PIN", example = "0000")
  private String pin;
  
}
