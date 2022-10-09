package io.github.girirajvyas.upi.wallet.controller;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import io.github.girirajvyas.upi.wallet.utils.QrCodeUtils;
import io.github.girirajvyas.upi.wallet.utils.UpiUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class QuickResponseCodeController {

  @PostMapping("/showQRCode")
  public String showQRCode(String pa, String pn, String amount, String tn, Model model) {
    log.info("/showQRCode anredirecting to /generateQRCode");
    String qrContent = UpiUtils.createMobileUpiString(pa, pn, amount, tn);
    model.addAttribute("qrCodeContent", "/generateQRCode?qrContent=" + qrContent);
    return "qr-code-view";
  }

  @GetMapping("/generateQRCode")
  public void generateQRCode(String qrContent, HttpServletResponse response) throws IOException {
    log.info("/generateQRCode called for genrating the QR code");
    String fileName = "Test";//need dynamic filename
    UpiUtils.getValuesFromUpiUrl(qrContent);

    byte[] qrCode = QrCodeUtils.createQRCodeDefaultSize(qrContent);
    QrCodeUtils.createQRCodeFile(qrCode, fileName);
    QrCodeUtils.decodeQRCodeFile(fileName);

    response.setContentType("image/png");
    OutputStream outputStream = response.getOutputStream();
    outputStream.write(qrCode);
  }

}
