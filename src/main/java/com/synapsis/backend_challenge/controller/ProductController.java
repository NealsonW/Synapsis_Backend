package com.synapsis.backend_challenge.controller;

import org.springframework.web.bind.annotation.RestController;

// import com.synapsis.backend_challenge.repository.CustomerRepository;
import com.synapsis.backend_challenge.repository.ProductRepository;

import com.synapsis.backend_challenge.model.Product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class ProductController {

        @Autowired
        private ProductRepository productRepository;

        // @Autowired
        // private CustomerRepository customerRepository;

        @GetMapping("/list")
        public ResponseEntity<List<Product>> getAllProducts(){

            List<Product> products = productRepository.findAll();

            if(products.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        }


}
