package it.academy.springexample.controller.api;

import it.academy.springexample.dto.CustomerDto;
import it.academy.springexample.entity.Customer;
import it.academy.springexample.mapper.CustomerMapper;
import it.academy.springexample.repository.CustomerRepository;
import it.academy.springexample.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/customers")
public class CustomerRestController {

    @Autowired
    CustomerRepository customerRepository;
    //@Autowired
    //CustomerMapper customerMapper;
    @Autowired
    CustomerService customerService;


    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<Customer>> index(Model model) {
        List<Customer> results = (List<Customer>) customerRepository.findAll();
        model.addAttribute("customers", results);
        return new ResponseEntity<List<Customer>>(results, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> show(@PathVariable("id") Long id, Model model) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping(value = {"", "/"})
    public Customer create(@RequestBody Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    @PutMapping("/{id}")
    public Customer update(@RequestBody Customer customer, @PathVariable long id) {
        return customerRepository.findById(id)
                .map(customerFound -> {
                    customerFound.setFirstName(customer.getFirstName());
                    customerFound.setLastName(customer.getLastName());
                    customerFound.setCc(customer.getCc());
                    customerFound.setActive(customer.isActive());
                    return customerRepository.save(customerFound);
                })
                .orElseGet(() -> {
                    customer.setId(id);
                    return customerRepository.save(customer);
                });
    }


        @DeleteMapping("/{id}")
        public void delete (@PathVariable Long id){
             customerRepository.deleteById(id);
        }


}