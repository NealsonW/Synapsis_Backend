package com.synapsis.backend_challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synapsis.backend_challenge.DTO.CartDTO;
import com.synapsis.backend_challenge.model.Cart;
import com.synapsis.backend_challenge.model.Customer;
import com.synapsis.backend_challenge.model.Product;
import com.synapsis.backend_challenge.repository.CartRepository;
import com.synapsis.backend_challenge.repository.CustomerRepository;
import com.synapsis.backend_challenge.repository.ProductRepository;


@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/insert/cart")
    public ResponseEntity<Cart> insertToCart(@RequestBody CartDTO cartDTO){
        
        if(!productRepository.existsById(cartDTO.getProductId())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Product product = productRepository.findByProductId(cartDTO.getProductId());

        if(product.getProductStock() < cartDTO.getQuantity()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(!customerRepository.existsById(cartDTO.getCustomerId())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer customer = customerRepository.findCustomerByCustomerId(cartDTO.getCustomerId());

        int totalPrice = (cartDTO.getQuantity() * product.getProductPrice());

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setCustomer(customer);;
        cart.setQuantity(cartDTO.getQuantity());
        cart.setTotal_price(totalPrice);
        cartRepository.save(cart);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/view/{customerId}/cart")
    public ResponseEntity<List<Cart>> getCartByCustomerId(@PathVariable(value = "customerId") int customerId){

        if(!cartRepository.existsByCustomerCustomerId(customerId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<Cart> carts = cartRepository.findByCustomerCustomerId(customerId);

        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "customerId") int customerId){
        Customer customer = customerRepository.findCustomerByCustomerId(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


    @DeleteMapping("/delete/cart/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable(value = "cartId") int cartId){

        if(!cartRepository.existsById(cartId)){
            return new ResponseEntity<>("Cart Doesn't Exist", HttpStatus.NOT_FOUND);
        }
        cartRepository.deleteById(cartId);


        return new ResponseEntity<>("Item Removed From Cart", HttpStatus.OK);
    }
}
