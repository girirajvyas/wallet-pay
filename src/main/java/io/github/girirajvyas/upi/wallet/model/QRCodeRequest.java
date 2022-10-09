package io.github.girirajvyas.upi.wallet.model;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class QRCodeRequest {
  
  @Parameter(description = "Virtual Private Address (VPA)", example = "john@okohdfcbank, 9876543210@okaxis")
  String virtualPaymentAddress;
  
  @Parameter(description = "Payer Name", example = "john doe")
  String payerName;

  @Parameter(description = "Transaction Amount", example = "100")
  String amount;
  
  @Parameter(description = "Transaction Note", example = "Grocery")
  String transactionNote;

}
