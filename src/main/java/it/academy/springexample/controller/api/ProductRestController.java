package it.academy.springexample.controller.api;

import it.academy.springexample.entity.Address;

import it.academy.springexample.entity.Product;
import it.academy.springexample.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {


    @Autowired
    ProductRepository productRepository;
    //GET ALL ADRESSES LIST
    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<Product>> index(Model model) {
        List<Product> results = (List<Product>) productRepository.findAll();
        model.addAttribute("product", results);
        return new ResponseEntity<List<Product>>(results, HttpStatus.OK);
    }

    //GET ADDRESS BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> show(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
        } else return ResponseEntity.notFound().build();
    }
    // CREATE NEW ADDRESS
    @PostMapping(value = {"", "/"})
    public Product create(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }
    //UPDATE ADDRESS BY ID
    @PutMapping("/{id}")
    public Product update(@RequestBody Product product, @PathVariable long id) {
        return productRepository.findById(id)
                .map(productFound -> {
                    productFound.setPrice(product.getPrice());
                    productFound.setProductDesc(product.getProductDesc());
                    productFound.setProductName(product.getProductName());
                    productFound.setWeight(product.getWeight());
                    return productRepository.save(productFound);
                })
                .orElseGet(() -> {
                    product.setId(id);
                    return productRepository.save(product);
                });
    }


    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id){
        productRepository.deleteById(id);
    }
}