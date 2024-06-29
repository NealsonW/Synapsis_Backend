package com.synapsis.backend_challenge.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @Column(name = "product_id")
    private int product_id;

    @Column(name = "category_id")
    private int category_id;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "product_price")
    private int product_price;

    @Column(name = "product_stock")
    private int product_stock;
}
