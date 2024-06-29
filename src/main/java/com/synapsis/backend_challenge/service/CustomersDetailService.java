package com.synapsis.backend_challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.synapsis.backend_challenge.model.Customer;
import com.synapsis.backend_challenge.repository.CustomerRepository;

public class CustomersDetailService implements UserDetailsService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        
        return new org.springframework.security.core.userdetails.User(customer.getUsername(),customer.getPassword(), null);
    }

}
