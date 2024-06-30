package com.synapsis.backend_challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synapsis.backend_challenge.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    List<Category> findCategoryByProductsProductId(int productId);
}
