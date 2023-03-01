package it.academy.springexample.controller;

import it.academy.springexample.entity.Customer;
import it.academy.springexample.repository.CustomerRepository;
import it.academy.springexample.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @GetMapping(value = {"","/","/index"})
    public String index(Model model)   {

        List<Customer> results = (List<Customer>) customerRepository.findAll();
        model.addAttribute( "customers",  results);
        return "customers/index";
    }
    @GetMapping(value = {"/active"})
    public String activeCustomers(Model model){
        List<Customer> results = (List<Customer>) customerService.getActiveCustomers();
        model.addAttribute( "customers",  results);
        return "customers/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model){
        Optional<Customer> customer = customerRepository.findById(id);
        model.addAttribute( "customer",  customer.get());
        model.addAttribute( "addresses",  customer.get().getAddresses());
        model.addAttribute( "products",  customer.get().getBoughtProducts());

        return "customers/show";
    }
}