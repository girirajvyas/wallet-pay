package io.github.girirajvyas.upi.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import io.github.girirajvyas.upi.wallet.model.Otp;
import io.github.girirajvyas.upi.wallet.model.User;
import io.github.girirajvyas.upi.wallet.service.OtpService;
import io.github.girirajvyas.upi.wallet.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RegistrationLoginController {

  @Autowired
  private OtpService otpService;

  @Autowired
  private UserService userService;
  
  @PostMapping("/loginUser")
  public String login(Long mobileNumber, Integer otp) {
    log.info("User with mobile number attampted login", mobileNumber);
    boolean verification = otpService.verifyOtp(mobileNumber, otp);
    return verification ? "verification" : "error";
  }
  
  @PostMapping("/signupUser")
  public String signUpUser(Long mobileNumber,  Model model) {
    Otp otp = otpService.generateOtp(mobileNumber);
    log.info("OTP generated: " + otp.getOtp());
    model.addAttribute("mobileNumber","/verifyOtp?mobileNumber="+mobileNumber);
    return "verify-otp";
  }
  
  @PostMapping("/verifyOtp")
  public String verifyOtp(Long mobileNumber, Integer otp,  Model model) {
    log.info("Verifying OTP {} against the mobile number {}", otp, mobileNumber);
    boolean verified = otpService.verifyOtp(mobileNumber,otp);
    model.addAttribute("mobileNumber","/registerUser?mobileNumber="+mobileNumber);
    log.info("OTP Verification status: " + verified);
    return "register-user";
  }
  
  
  @PostMapping("/registerUser")
  public String registerUser(String firstName, String lastName, Long mobileNumber, String emailId,  Model model) {
    log.info("/registerUser"+mobileNumber);
    userService.saveUser(new User(firstName, lastName, mobileNumber, emailId));
    //model.addAttribute("qrCodeContent", "/generateQRCode?qrContent=" + qrContent);
    return "pin-setup";
  }
  
  @PostMapping("/setupPin")
  public String setupPin(String firstName, String lastName, Long mobileNumber, String emailId,  Model model) {
    log.info("/registerUser"+mobileNumber);
    userService.saveUser(new User(firstName, lastName, mobileNumber, emailId));
    //model.addAttribute("qrCodeContent", "/generateQRCode?qrContent=" + qrContent);
    return "pin-setup";
  }
  
}
