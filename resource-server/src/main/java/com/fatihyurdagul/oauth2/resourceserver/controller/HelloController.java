package com.fatihyurdagul.oauth2.resourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  
  @GetMapping(path = "/greet")
  public String sayHello() {
    return "Hello World !!";
  }
}
