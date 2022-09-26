package io.github.girirajvyas.model;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  // @Parameter(description = "User ID", example = "john doe")
  // private Long userId;

  @Parameter(description = "First Name", example = "john")
  private String firstName;

  @Parameter(description = "Last Name", example = "doe")
  private String lastName;

  @Parameter(description = "Mobile Number", example = "9876543210")
  private Long mobileNumber;

  @Parameter(description = "Email Id", example = "john.doe@gmail.com")
  private String emailId;

}
