package io.github.girirajvyas.controllerrest;

import java.util.Map;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.girirajvyas.exception.model.PayErrorsWrapper;
import io.github.girirajvyas.model.QRCodeRequest;
import io.github.girirajvyas.utils.QrCodeUtils;
import io.github.girirajvyas.utils.UpiUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "QR Code APIs", description = "QR code related operations for users")
@Slf4j
@RestController
public class QRCodeRestController {

  @PostMapping(value = "/qr-code")
  @Operation(summary = "Generate QR Code")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Created",
          content = {@Content(mediaType = MediaType.IMAGE_PNG_VALUE)}),
      @ApiResponse(responseCode = "400", description = "Invalid input supplied",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = PayErrorsWrapper.class))}),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
  public ResponseEntity<byte[]> generateQRCode(@ParameterObject QRCodeRequest qrCodeRequest) {

    log.info("generating qr code via API" + qrCodeRequest);
    String qrContent = UpiUtils.createMobileUpiStringApi(qrCodeRequest.getVirtualPaymentAddress(),
        qrCodeRequest.getPayerName(), qrCodeRequest.getAmount(),
        qrCodeRequest.getTransactionNote());

    Map<String, String> map = UpiUtils.getValuesFromUpiUrl(qrContent);
    map.entrySet().forEach(entry -> log.info(entry.getKey() + ":" + entry.getValue()));

    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_PNG);
    
    byte[] bs = QrCodeUtils.createQRCodeDefaultSize(qrContent);
    QrCodeUtils.createQRCodeFile(bs, qrCodeRequest.getVirtualPaymentAddress());
    
    return new ResponseEntity<>(bs, headers, HttpStatus.CREATED);
  }

  @GetMapping(value = "/qr-code")
  @Operation(summary = "Read QR Code for give VPA")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful",
          content = {@Content(mediaType = MediaType.IMAGE_PNG_VALUE)}),
      @ApiResponse(responseCode = "400", description = "Invalid input supplied",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = PayErrorsWrapper.class))}),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
  public ResponseEntity<byte[]> getQRCode(@Parameter(description = "Virtual Private Address (VPA)", example = "john@okohdfcbank, john@okaxis") String virtualPaymentAddress) {
    byte[] bs = QrCodeUtils.readQRCodeFile(virtualPaymentAddress);
    return new ResponseEntity<>(bs, HttpStatus.OK);
  }
}
