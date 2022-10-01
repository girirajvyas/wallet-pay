package io.github.girirajvyas.controllerrest;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.girirajvyas.exception.model.PayErrorsWrapper;
import io.github.girirajvyas.model.Otp;
import io.github.girirajvyas.model.Pin;
import io.github.girirajvyas.service.PinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Acccont APIs", description = "Account related operations for users")
@Slf4j
@RestController
public class AccountRestController {

  @Autowired
  private PinService pinService;

  @PostMapping("/pin")
  @Operation(summary = "Set PIN")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Successful",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Pin.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid input supplied",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = PayErrorsWrapper.class))}),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
  public ResponseEntity<Pin> setPin(@ParameterObject Pin pin) {
    log.info("Setting PIN for Mobile number: " + pin.getMobileNumber());
    Pin updatedPin = pinService.setPin(pin);
    return new ResponseEntity<>(updatedPin, HttpStatus.CREATED);
  }
  
  @GetMapping("/account")
  @Operation(summary = "Fetch Account Details")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful login",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Otp.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid input supplied",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = PayErrorsWrapper.class))}),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
  public ResponseEntity<Otp> getOtp(Long mobileNumber) {
   
    
    
    
    return null;
  }

  
  

}
