package com.example.demo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.example.demo.model.Customer;
import com.example.demo.model.MovieDetail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CustomerRepository {

    final private DynamoDBMapper dynamoDBMapper;

    public Customer createCustomer(Customer customer){
        dynamoDBMapper.save(customer);
        return customer;
    }

    public Customer getCustomerById(String id){
        return dynamoDBMapper.load(Customer.class, id);
    }

    public PaginatedScanList<Customer> getCustomerList(){
        DynamoDBScanExpression filter =  new DynamoDBScanExpression();
        return dynamoDBMapper.scan(Customer.class, filter);
    }

    public Customer updateCustomer(String id, Customer customer){
        Customer load = dynamoDBMapper.load(Customer.class, id);
        // map these two entity
        load.setName(customer.getName());
        load.setAge(customer.getAge());
        load.setActiveFlag(customer.getActiveFlag());
        dynamoDBMapper.save(load);

        return dynamoDBMapper.load(Customer.class, id);
    }

    public String deleteCustomer(String id){
        Customer load = dynamoDBMapper.load(Customer.class, id);
        if(load != null){
            dynamoDBMapper.delete(load);
            return load.getId() + " get deleted !";
        } else {
            return null;
        }

    }
}