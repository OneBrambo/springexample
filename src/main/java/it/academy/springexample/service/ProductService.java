package it.academy.springexample.service;

import it.academy.springexample.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ProductService")
public class ProductService {

    @Autowired
    ProductRepository productRepository;

}
