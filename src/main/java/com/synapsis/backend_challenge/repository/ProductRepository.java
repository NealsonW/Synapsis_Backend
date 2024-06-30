package com.synapsis.backend_challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synapsis.backend_challenge.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    List<Product> findAll();
    List<Product> findProductsByProductCategoriesCategoryId(int categoryId);
    
    Product findByProductId(int productId);

}
