package it.academy.springexample.service;


import it.academy.springexample.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("AddressService")
public class AddressService {

    @Autowired
    AddressRepository addressRepository;


}
