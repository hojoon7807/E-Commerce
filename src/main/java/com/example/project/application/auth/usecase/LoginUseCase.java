package com.example.project.application.auth.usecase;

import com.example.project.domain.user.model.User;
import java.util.function.Function;

public interface LoginUseCase extends Function<LoginCommand, User> {

}
