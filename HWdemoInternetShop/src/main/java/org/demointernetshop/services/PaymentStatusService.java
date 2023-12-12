package org.demointernetshop.services;

import lombok.RequiredArgsConstructor;
import org.demointernetshop.entity.OrderStatus;
import org.demointernetshop.entity.PaymentStatus;
import org.demointernetshop.repository.OrderStatusRepository;
import org.demointernetshop.repository.PaymentStatusRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentStatusService {
    private final PaymentStatusRepository paymentStatusRepository;
    public PaymentStatus getPaymentStatusByName(String paymentstatus){
        return paymentStatusRepository.findByStatus(paymentstatus)
                .orElseThrow(() -> new RuntimeException("OrderStatus not found: " + paymentstatus));

    }
}
