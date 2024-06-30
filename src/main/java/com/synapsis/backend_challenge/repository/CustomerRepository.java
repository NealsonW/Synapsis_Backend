package com.synapsis.backend_challenge.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synapsis.backend_challenge.model.Customer;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

    Optional<Customer> findByUsername(String username);
    // Optional<Customer> findByCustomer_id(int customer_id);

    Boolean existsByUsername(String username);

}
