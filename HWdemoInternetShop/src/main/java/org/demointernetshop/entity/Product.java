package org.demointernetshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;
    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
 //   @JoinColumn(name = "productinfo_id")
    private ProductInfo productInfo;

    public Product(Integer id) {
        this.id = id;
    }
}
