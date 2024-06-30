package com.synapsis.backend_challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.synapsis.backend_challenge.model.Product;

import com.synapsis.backend_challenge.repository.CategoryRepository;
import com.synapsis.backend_challenge.repository.ProductRepository;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/category/{categoryId}/products")
    public ResponseEntity<List<Product>> getAllProductsByCategoryId(@PathVariable(value = "categoryId") int categoryId){
        if(!categoryRepository.existsById(categoryId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Product> products = productRepository.findProductsByProductCategoriesCategoryId(categoryId);

        return new ResponseEntity<>(products, HttpStatus.OK);

    }

}
