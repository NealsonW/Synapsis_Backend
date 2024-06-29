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
@Table(name = "carts")
public class Cart {
    
    @Id
    @Column(name = "cart_id")
    private int cart_id;

    @Column(name = "customer_id")
    private int customer_id;

    @Column(name = "product_id")
    private int product_id;

    @Column(name = "total_price")
    private int total_price;
}
