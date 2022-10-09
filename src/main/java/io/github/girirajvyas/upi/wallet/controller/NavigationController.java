package io.github.girirajvyas.upi.wallet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController {

  @RequestMapping("/")
  public String index() {
    return "index";
  }
  
  @RequestMapping("/signup-user")
  public String signupUser() {
    return "signup-user";
  }
  
  @RequestMapping("/register-user")
  public String register() {
    return "register-user";
  }
  
  @RequestMapping("/login-user")
  public String login() {
    return "login-user";
  }
  
  @RequestMapping("/qr-code-generate")
  public String generateQr() {
    return "qr-code-generate";
  }

}
