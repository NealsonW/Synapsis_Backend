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
@Table(name = "categories")
public class Category {
    
    @Id
    @Column(name = "category_id")
    private int category_id;

    @Column(name = "product_id")
    private int product_id;

    @Column(name = "category_name")
    private String category_name;
}
