package org.demointernetshop.repository;

import org.demointernetshop.dto.product.ProductQuantityResponseDto;
import org.demointernetshop.entity.Category;
import org.demointernetshop.entity.Manufacturer;
import org.demointernetshop.entity.PaymentMethod;
import org.demointernetshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    List<Product> findByCategoryAndProductInfoPriceBetween(Category category, BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> findByCategoryAndManufacturerAndProductInfoPriceBetween(
            Category category, Manufacturer manufacturer, BigDecimal minPrice, BigDecimal maxPrice);
    ProductQuantityResponseDto findProductDtoQuantity(Integer productId);
    Product findProductById(Integer productId);
}

