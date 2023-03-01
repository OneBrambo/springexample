package it.academy.springexample.service;

import it.academy.springexample.entity.Customer;
import it.academy.springexample.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("CustomerService")
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getActiveCustomers(){
        return customerRepository.findByActive(true);
}
}
