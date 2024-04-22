package com.example.project.presentation.user.controller;

import com.example.project.application.user.usecase.GetMyPageUseCase;
import com.example.project.application.user.usecase.MyPageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetMyPageController {

  private final GetMyPageUseCase getMyPageUseCase;
  @GetMapping("/my-page")
  public ResponseEntity<MyPageInfo> myPage(@AuthenticationPrincipal UserDetails userDetails){
    Long userId = Long.valueOf(userDetails.getUsername());

    MyPageInfo myPageInfo = getMyPageUseCase.apply(userId);
    return ResponseEntity.ok(myPageInfo);
  }
}
