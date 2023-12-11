package org.demointernetshop.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.demointernetshop.dto.product.ProductDto;
import org.demointernetshop.dto.product.ProductQuantityResponseDto;
import org.demointernetshop.entity.Product;
import org.demointernetshop.repository.ProductRepository;
import org.demointernetshop.services.utils.Converters;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(Converters::productToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getProductsByCategoryAndPrice(Integer categoryId, Double minPrice, Double maxPrice) {
        // Implement this method based on your requirements
        // You may need to add a custom query in the repository or use existing methods
        // Example: productRepository.findByCategoryAndPriceBetween(categoryId, minPrice, maxPrice)
        return Collections.emptyList();
    }

    public List<ProductDto> getProductsByCategoryAndManufacturerAndPrice(Integer categoryId, Integer manufacturerId,
                                                                         Double minPrice, Double maxPrice) {
        // Implement this method based on your requirements
        // Example: productRepository.findByCategoryAndManufacturerAndPriceBetween(categoryId, manufacturerId, minPrice, maxPrice)
        return Collections.emptyList();
    }

    public ProductDto getProductDtoById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
        return Converters.productToDto(product);
    }
    public Product getProductById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
        return product;
    }


    public ProductQuantityResponseDto getProductQuantity(Integer productId) {
        // Implement this method based on your requirements
        // Example: productRepository.findProductQuantity(productId)
        return new ProductQuantityResponseDto();
    }
}