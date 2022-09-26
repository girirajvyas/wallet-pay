package io.github.girirajvyas.controllerrest;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.girirajvyas.exception.model.PayErrorsWrapper;
import io.github.girirajvyas.model.User;
import io.github.girirajvyas.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Registration APIs", description = "Registration related operations for users")
@Slf4j
@RestController
public class RegistrationRestController {

  @Autowired
  private UserService userService;

  @PostMapping(value = "/register")
  @Operation(summary = "Register user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Created",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid input supplied",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = PayErrorsWrapper.class))}),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
  public ResponseEntity<User> register(@ParameterObject User user) {
    log.info("User with mobile number attempted login", user.getMobileNumber());

    User savedUser = userService.saveUser(user);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }

}
