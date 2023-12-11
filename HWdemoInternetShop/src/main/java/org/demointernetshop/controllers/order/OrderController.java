package org.demointernetshop.controllers.order;

import lombok.RequiredArgsConstructor;
import org.demointernetshop.dto.cart.CartChangeDto;
import org.demointernetshop.dto.cart.CartDto;
import org.demointernetshop.dto.order.OrderDto;
import org.demointernetshop.dto.order.OrderRequestDto;
import org.demointernetshop.services.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{cart_id}")
    public OrderDto createOrder(@PathVariable("cart_id") Integer cartId, @RequestBody OrderRequestDto request){
        return orderService.createOrder(cartId,request);
    }

    @DeleteMapping("{order_id}")
    public void deleteOrder(@PathVariable("order_id") Integer orderId){
       orderService.deleteOrder(orderId);
       // return orderService.deleteOrder(orderId);
    }



}
