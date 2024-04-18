package com.example.project.application.usecase;

import com.example.project.domain.user.model.User;
import java.util.function.Function;

public interface SignUpUseCase extends Function<SignUpCommand, User> {

}
