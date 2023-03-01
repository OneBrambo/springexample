package it.academy.springexample.repository;

import it.academy.springexample.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    public List<Customer>findByActive(Boolean status);

}
