package it.academy.springexample.repository;

import it.academy.springexample.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}
