package com.example.project.application.user.usecase;

import com.example.project.domain.user.model.Address;
import java.util.function.Function;

public interface UpdateAddressUseCase extends Function<UpdateAddressCommand, Address>{

}
