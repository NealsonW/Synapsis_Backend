package com.synapsis.backend_challenge.controller;

import org.springframework.web.bind.annotation.RestController;

import com.synapsis.backend_challenge.DTO.LoginDTO;
import com.synapsis.backend_challenge.DTO.SignUpDTO;
import com.synapsis.backend_challenge.model.Customer;
import com.synapsis.backend_challenge.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody LoginDTO loginDTO){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerCustomer(@RequestBody SignUpDTO signUpDto){

        if(customerRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        Customer customer = new Customer();
        customer.setUsername(signUpDto.getUsername());
        customer.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        customerRepository.save(customer);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
