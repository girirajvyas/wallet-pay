package io.github.girirajvyas.controllerrest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestController
public class TestRestController {

  @GetMapping("/test")
  public String test() {
    return "Test Success!";
  }

}
