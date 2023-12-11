package org.demointernetshop.repository;

import org.demointernetshop.entity.Cart;
import org.demointernetshop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
