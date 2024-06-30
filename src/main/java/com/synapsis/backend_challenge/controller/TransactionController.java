package com.synapsis.backend_challenge.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synapsis.backend_challenge.DTO.TransactionDTO;
import com.synapsis.backend_challenge.model.Customer;
import com.synapsis.backend_challenge.model.Product;
import com.synapsis.backend_challenge.model.Cart;
import com.synapsis.backend_challenge.model.Transaction;
import com.synapsis.backend_challenge.repository.CartRepository;
import com.synapsis.backend_challenge.repository.CustomerRepository;
import com.synapsis.backend_challenge.repository.ProductRepository;
import com.synapsis.backend_challenge.repository.TransactionRepository;


@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/transaction/insert")
    public ResponseEntity<Transaction> insertNewTransaction(@RequestBody TransactionDTO transactionDTO){
        if(!customerRepository.existsById(transactionDTO.getCustomerId())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Customer customer = customerRepository.findCustomerByCustomerId(transactionDTO.getCustomerId());

        if(!cartRepository.existsById(transactionDTO.getCartId())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Cart cart = cartRepository.findByCartId(transactionDTO.getCartId());

        Date date = new Date((System.currentTimeMillis()));

        Transaction transaction = new Transaction();
        transaction.setCart(cart);
        transaction.setCustomer(customer);
        transaction.setStatus("UNPAID");
        transaction.setDate(date);
        transactionRepository.save(transaction);

        return new ResponseEntity<>(transaction, HttpStatus.OK);
        
    }

    @PutMapping("/transaction/checkout/{transactionId}")
    public ResponseEntity<?> checkoutTransaction(@PathVariable(value = "transactionId") int transactionId){
        
        if(!transactionRepository.existsById(transactionId)){
            return new ResponseEntity<>("Transaksi Tidak Ada", HttpStatus.NOT_FOUND);
        }


        Transaction transaction = transactionRepository.findByTransactionId(transactionId);

        if(transaction.getStatus().equals("PAID")){
            return new ResponseEntity<>("Transaksi Sudah Dibayar", HttpStatus.BAD_REQUEST);
        }

        Cart cart = cartRepository.findByCartId(transaction.getCart().getCartId());

        Product product = productRepository.findByProductId(cart.getProduct().getProductId());
        
        int stockAfter = (product.getProductStock() - cart.getQuantity());

        product.setProductStock(stockAfter);

        transaction.setStatus("PAID");

        productRepository.save(product);
        transactionRepository.save(transaction);

        return new ResponseEntity<>("Pembayaran Selesai", HttpStatus.OK);

    }
}
