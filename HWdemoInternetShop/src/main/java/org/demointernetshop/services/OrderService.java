package org.demointernetshop.services;

import lombok.RequiredArgsConstructor;
import org.demointernetshop.dto.cart.CartDto;
import org.demointernetshop.dto.order.OrderDto;
import org.demointernetshop.dto.order.OrderRequestDto;
import org.demointernetshop.dto.product.ProductShortInfoDto;
import org.demointernetshop.entity.Order;
import org.demointernetshop.entity.OrderItem;
import org.demointernetshop.entity.Product;
import org.demointernetshop.entity.User;
import org.demointernetshop.repository.OrderRepository;
import org.demointernetshop.services.utils.Converters;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService; // CartService
    private final OrderStatusService orderStatusService; // OrderStatusService
    private final PaymentMethodService paymentMethodService; //  PaymentMethodService
    private final PaymentStatusService paymentStatusService; // PaymentStatusService


    //todo:OrderStatusService,PaymentMethodService,PaymentStatusService
    public OrderDto createOrder(Integer cartId, OrderRequestDto request) {
        CartDto cartDto = cartService.getCart(cartId);
        User user = new User();
        user.setId(request.getUserId()); // Assuming the User ID is provided in the request

        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(convertCartItemsToOrderItems(cartDto.getProducts(), order));
        order.setTotalAmount(calculateTotalAmount(cartDto.getProducts()));

        // orderStatusService.getOrderStatusByName("pending")
        order.setOrderStatus("pending"); // Set default status
       // paymentMethodService.getPaymentMethodByName("default")
        order.setPaymentMethod("default"); // Set default method
        //paymentStatusService.getPaymentStatusByName("unpaid")
        order.setPaymentStatus("unpaid"); // Set default status
        order.setCreateData(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);
        return Converters.orderToDto(savedOrder);
    }

    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    private List<OrderItem> convertCartItemsToOrderItems(List<ProductShortInfoDto> cartProducts, Order order) {
        return cartProducts.stream()
                .map(productDto -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(new Product(productDto.getId())); // Assuming Product constructor with ID
                    orderItem.setQuantity(productDto.getQuantity());
                    orderItem.setPrice(productDto.getPrice());
                    orderItem.setOrder(order);
                    return orderItem;
                })
                .collect(Collectors.toList());
    }

    private BigDecimal calculateTotalAmount(List<ProductShortInfoDto> products) {
        return products.stream()
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}