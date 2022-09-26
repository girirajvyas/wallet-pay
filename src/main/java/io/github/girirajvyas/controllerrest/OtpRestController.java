package io.github.girirajvyas.controllerrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.girirajvyas.exception.PayValidationException;
import io.github.girirajvyas.exception.model.PayErrorsWrapper;
import io.github.girirajvyas.model.Otp;
import io.github.girirajvyas.service.OtpService;
import io.github.girirajvyas.utils.ValidatorUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "OTP APIs", description = "OTP related operations for users")
@RestController
public class OtpRestController {

  @Autowired
  private OtpService otpService;

  @PostMapping("/otp")
  @Operation(summary = "Generate OTP")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful login",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Otp.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid input supplied",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = PayErrorsWrapper.class))}),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
  public ResponseEntity<Otp> generateOtp(Long mobileNumber) {
    ValidatorUtils.isValidMobileNumber(mobileNumber);
    log.info("Generating OTP for Mobile number: " + mobileNumber);
    Otp otp = otpService.generateOtp(mobileNumber);
    return new ResponseEntity<>(otp, HttpStatus.CREATED);
  }

  // temporary API to fetch otp
  @GetMapping("/otp")
  @Operation(summary = "Fetch generated OTP - Temporary API for testing purpose")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful login",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Otp.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid input supplied",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = PayErrorsWrapper.class))}),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
  public ResponseEntity<Otp> getOtp(Long mobileNumber) {
    ValidatorUtils.isValidMobileNumber(mobileNumber);
    Otp otp = otpService.getOtp(mobileNumber);
    return new ResponseEntity<>(otp, HttpStatus.OK);
  }

  @GetMapping("/otp/verify")
  @Operation(summary = "Verify the OTP")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Otp.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid input supplied",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = PayErrorsWrapper.class))}),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
  public ResponseEntity<Boolean> verifyOtp(Long mobileNumber, Integer otp) {
    ValidatorUtils.isValidMobileNumber(mobileNumber);
    log.info("Verifying mobile number {} for Otp {}", mobileNumber, otp);

    boolean success = otpService.verifyOtp(mobileNumber, otp);
    if (!success) {
      throw new PayValidationException("Otp does not match!");
    }

    return new ResponseEntity<>(success, HttpStatus.OK);
  }

}
