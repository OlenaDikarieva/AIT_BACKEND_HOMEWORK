package org.demointernetshop.services;

import lombok.RequiredArgsConstructor;
import org.demointernetshop.dto.cart.CartDto;
import org.demointernetshop.entity.OrderStatus;
import org.demointernetshop.repository.CartRepository;
import org.demointernetshop.repository.OrderStatusRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;
    public OrderStatus getOrderStatusByName(String orderstatus){
        return orderStatusRepository.findByStatus(orderstatus)
                .orElseThrow(() -> new RuntimeException("OrderStatus not found: " + orderstatus));

    }
}
