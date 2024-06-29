package com.synapsis.backend_challenge.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
// @Table(name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "customer_id")
    private int customer_id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;
}
