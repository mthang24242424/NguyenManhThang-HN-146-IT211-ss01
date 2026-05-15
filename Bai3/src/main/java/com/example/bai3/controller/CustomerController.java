package com.example.bai3.controller;

import com.example.bai3.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private List<Customer> customers = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong(1);

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customer.setId(nextId.getAndIncrement());
        customers.add(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Optional<Customer> existingCustomer = customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        if (existingCustomer.isPresent()) {
            Customer c = existingCustomer.get();
            c.setName(customer.getName());
            c.setEmail(customer.getEmail());
            return new ResponseEntity<>(c, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
