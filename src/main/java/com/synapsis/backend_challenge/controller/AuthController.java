package com.synapsis.backend_challenge.controller;

import org.springframework.web.bind.annotation.RestController;

import com.synapsis.backend_challenge.DTO.LoginDTO;
import com.synapsis.backend_challenge.DTO.SignUpDTO;
import com.synapsis.backend_challenge.model.Customer;
import com.synapsis.backend_challenge.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody LoginDTO loginDTO){
        if(!customerRepository.existsByUsernameOrPassword(loginDTO.getUsername(), loginDTO.getPassword())){
            return new ResponseEntity<>("Wrong Username or Password",HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerCustomer(@RequestBody SignUpDTO signUpDto){

        if(customerRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        Customer customer = new Customer();
        customer.setUsername(signUpDto.getUsername());
        customer.setPassword(signUpDto.getPassword());

        customerRepository.save(customer);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
