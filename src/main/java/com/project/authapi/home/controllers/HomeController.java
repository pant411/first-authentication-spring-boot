package com.project.authapi.home.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class HomeController {
  @GetMapping
  public String home() {
    return "Hello World"; // This will return "index.html" (or a template name) as the response
  }
}
