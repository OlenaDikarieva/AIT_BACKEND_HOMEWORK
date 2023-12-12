package org.demointernetshop.repository;

import org.demointernetshop.entity.Cart;
import org.demointernetshop.entity.PaymentMethod;
import org.demointernetshop.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
    Optional<PaymentMethod> findByMethod(String metod);
}
