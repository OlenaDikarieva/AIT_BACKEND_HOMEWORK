package org.demointernetshop.repository;

import org.demointernetshop.entity.Cart;
import org.demointernetshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
