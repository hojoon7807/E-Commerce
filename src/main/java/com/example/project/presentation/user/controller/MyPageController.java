package com.example.project.presentation.user.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyPageController {

  @GetMapping("/my-page")
  public String myPage(@AuthenticationPrincipal UserDetails userDetails){
    System.out.println(userDetails.getUsername());
    return "hi";
  }
}
