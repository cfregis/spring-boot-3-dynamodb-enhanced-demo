package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customersRepository;

    @PostMapping("/customers")
    public Customer addCustomers(@RequestBody Customer customer) {
        Customer updatedCustomer = customersRepository.createCustomer(customer);
        return updatedCustomer;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customersRepository.getCustomerList();
    }

    @GetMapping("/customers/{id}")
    public Customer getOneCustomer(@PathVariable("id") String id) {
        return customersRepository.getCustomerById(id);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteOneCustomer(@PathVariable("id") String id) {
        customersRepository.deleteCustomer(id);
    }
}