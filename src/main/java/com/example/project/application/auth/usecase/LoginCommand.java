package com.example.project.application.auth.usecase;

public record LoginCommand(
    String email,
    String password
) {

}
