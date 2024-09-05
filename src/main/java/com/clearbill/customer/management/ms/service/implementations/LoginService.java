package com.clearbill.customer.management.ms.service.implementations;

import com.clearbill.customer.management.ms.entity.Customer;
import com.clearbill.customer.management.ms.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginService implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<Customer> customer = customerRepo.findByEmail(s);

        if(customer.isEmpty()) throw  new UsernameNotFoundException("User name not found");

        Customer customerDetails = customer.orElse(null);

        return User.builder().username(customerDetails.getEmail()).password(passwordEncoder.encode(customerDetails.getPassword())).
                build();
    }

}
