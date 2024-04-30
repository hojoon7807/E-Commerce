package com.example.project.application.payment.usecase;

import com.example.project.domain.payment.Payment;
import java.util.function.Function;

public interface ApprovePaymentUseCase extends Function<ApprovePaymentCommand, Payment> {

}
