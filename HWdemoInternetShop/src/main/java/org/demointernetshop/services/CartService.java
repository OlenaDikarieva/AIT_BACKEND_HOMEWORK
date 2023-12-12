package org.demointernetshop.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.demointernetshop.dto.cart.CartChangeDto;
import org.demointernetshop.dto.cart.CartDto;
import org.demointernetshop.dto.product.ProductDto;
import org.demointernetshop.entity.Cart;
import org.demointernetshop.entity.CartItem;
import org.demointernetshop.entity.Product;
import org.demointernetshop.entity.User;
import org.demointernetshop.repository.CartItemRepository;
import org.demointernetshop.repository.CartRepository;
import org.demointernetshop.services.utils.Converters;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    private final ProductService productService; // Assuming you have a ProductService

    public CartDto getCart(Integer userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for user with id: " + userId));

        return Converters.cartToDto(cart);
    }

    public CartDto updateCart(Integer userId, CartChangeDto request) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    User user = new User();
                    user.setId(userId);
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
        Product product = productService.getProductById(request.getProduct_id());
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(request.getProduct_id()))
                .findFirst()
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setProduct(product);
                    newItem.setQuantity(0);
                    newItem.setCart(cart);
                    cart.getCartItems().add(newItem);
                    return newItem;
                });

        cartItem.setQuantity(request.getCount());
        cartRepository.save(cart);

        return Converters.cartToDto(cart);
    }
}
