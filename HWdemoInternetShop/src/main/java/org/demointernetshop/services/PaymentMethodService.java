package org.demointernetshop.services;

import lombok.RequiredArgsConstructor;
import org.demointernetshop.entity.OrderStatus;
import org.demointernetshop.entity.PaymentMethod;
import org.demointernetshop.repository.ManufacturerRepository;
import org.demointernetshop.repository.OrderStatusRepository;
import org.demointernetshop.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;
    public PaymentMethod getPaymentMethodByName(String metod){
        return paymentMethodRepository.findByMethod(metod)
                .orElseThrow(() -> new RuntimeException("OrderMetod not found: " + metod));

    }
}
