package com.synapsis.backend_challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synapsis.backend_challenge.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

    Boolean existsByCustomerCustomerId(int customerId);
    Boolean existsByProductProductId(int productId);

    Cart findByCustomerCustomerIdAndProductProductId(int customerId, int productId);
    Cart findByCartId(int cartId);
    Cart deleteByCartId(int cartId);

    List<Cart> findByCustomerCustomerId(int customerId);
    List<Cart> findByProductProductId(int productId);

    Cart deleteByCustomerCustomerIdAndProductProductId(int customerId, int productId);

}
