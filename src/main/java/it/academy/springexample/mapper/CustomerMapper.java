package it.academy.springexample.mapper;

import it.academy.springexample.controller.api.CustomerRestController;
import it.academy.springexample.dto.CustomerDto;
import it.academy.springexample.entity.Customer;
import it.academy.springexample.controller.api.CustomerRestController;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;


@Mapper

public interface CustomerMapper  {

    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto destination);
}
