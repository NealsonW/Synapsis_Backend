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
@Table(name = "transactions")
public class Transaction {
    
    @Id
    @Column(name = "transaction_id")
    private int transaction_id;

    @Column(name = "customer_id")
    private int customer_id;

    @Column(name = "cart_id")
    private int cart_id;

    @Column(name = "date")
    private String date;

    @Column(name = "status")
    private String status;
}
