package org.demointernetshop.services.utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.demointernetshop.dto.cart.CartDto;
import org.demointernetshop.dto.order.OrderDto;
import org.demointernetshop.dto.product.*;
import org.demointernetshop.dto.user.UserDto;
import org.demointernetshop.dto.user.UserRegistrationDto;
import org.demointernetshop.entity.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class Converters {
    public CategoryDto formCategoryToDto(Category category) {
        return new CategoryDto(category.getId(), category.getCategoryName());
    }

    public static UserDto entityToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole().getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .createData(user.getCreateData())
                .lastVisitData(user.getLastVisitData())
                .build();
    }

    public static User registrationDtoToEntity(UserRegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(registrationDto.getPassword());
        user.setEmail(registrationDto.getEmail());
        user.setPhone(registrationDto.getPhone());
        // Set other fields as needed
        return user;
    }

    public static ManufacturerDto manufacturerToDto(Manufacturer manufacturer) {
        return ManufacturerDto.builder()
                .id(manufacturer.getId())
                .name(manufacturer.getName())
                .country(manufacturer.getCountry())
                .build();
    }
    //todo product.getProductInfo();
    public static ProductDto productToDto(Product product) {
        ProductInfo productInfo = product.getProductInfo();
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(CategoryDto.from(product.getCategory()))
                .manufacturer(ManufacturerDto.from(product.getManufacturer()))
                .productPrice(productInfo != null ? productInfo.getPrice() : null)
                .productQuantity(productInfo != null ? productInfo.getQuantity() : null)
                .build();
    }
    //todo product.getProductInfo();
    public static ProductShortInfoDto productToShortInfoDto(Product product) {
        return ProductShortInfoDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getProductInfo().getPrice())
                .quantity(product.getProductInfo().getQuantity())
                .build();
    }
    //todo product.getProductInfo();
    public static ProductShortResponseDto productToShortResponseDto(Product product) {
        return ProductShortResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .productPrice(product.getProductInfo().getPrice())
                .productQuantity(product.getProductInfo().getQuantity())
                .build();

    }

    public static CartDto cartToDto(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .products(cart.getCartItems().stream()
                        .map(Converters::cartItemToProductShortInfoDto)
                        .collect(Collectors.toList()))
                .build();
    }
////todo product.getProductInfo();
    public static ProductShortInfoDto cartItemToProductShortInfoDto(CartItem cartItem) {
        return ProductShortInfoDto.builder()
                .id(cartItem.getProduct().getId())
                .name(cartItem.getProduct().getName())
                .price(cartItem.getProduct().getProductInfo().getPrice())
                .quantity(cartItem.getQuantity())
                .build();
    }
    //todo OrderDto
    public static OrderDto orderToDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .products(order.getOrderItems().stream()
                        .map(Converters::orderItemToProductShortInfoDto)
                        .collect(Collectors.toList()))
                .orderStatus(order.getOrderStatus().getStatus())
                .paymentStatus(order.getPaymentStatus().getStatus())
                .paymentMethod(order.getPaymentMethod().getMethod())
                .createData(order.getCreateData())
                .build();
    }
    public static ProductShortInfoDto orderItemToProductShortInfoDto(OrderItem orderItem) {
        return ProductShortInfoDto.builder()
                .id(orderItem.getProduct().getId())
                .name(orderItem.getProduct().getName())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .build();
    }
}
