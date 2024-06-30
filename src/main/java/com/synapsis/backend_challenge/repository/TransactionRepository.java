package com.synapsis.backend_challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synapsis.backend_challenge.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

    Transaction findByTransactionId(int transactionId);
    Transaction findByStatus(String status);
    Transaction findByCartCartId(int cartId);
}
