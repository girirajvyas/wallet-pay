package io.github.girirajvyas.model;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class Otp {

  @Parameter(description = "Mobile Number", example = "9876543210")
  private Long mobileNumber;

  @Parameter(description = "One Time Password", example = "0000")
  private String otp;
  
}
