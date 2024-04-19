package com.example.project.application.user.usecase;

public record SignUpCommand(
    String username,
    String password,
    String email,
    String phoneNum,
    String zipcode,
    String addressMain,
    String addressSub
) {

}
