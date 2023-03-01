package it.academy.springexample.controller.api;

import it.academy.springexample.entity.Address;
import it.academy.springexample.entity.Customer;
import it.academy.springexample.repository.AddressRepository;
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
@RequestMapping("/api/addresses")
public class AddressRestController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;
    @Autowired
    AddressRepository addressRepository;
//GET ALL ADRESSES LIST
    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<Address>> index(Model model) {
        List<Address> results = (List<Address>) addressRepository.findAll();
        model.addAttribute("address", results);
        return new ResponseEntity<List<Address>>(results, HttpStatus.OK);
    }

    //GET ADDRESS BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Address> show(@PathVariable("id") Long id, Model model) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            return new ResponseEntity<Address>(address.get(), HttpStatus.OK);
        } else return ResponseEntity.notFound().build();
    }
// CREATE NEW ADDRESS
    @PostMapping(value = {"", "/"})
    public Address create(@RequestBody Address newAddress) {
        return addressRepository.save(newAddress);
    }
//UPDATE ADDRESS BY ID
    @PutMapping("/{id}")
    public Address update(@RequestBody Address address, @PathVariable long id) {
        return addressRepository.findById(id)
                .map(addressFound -> {
                    addressFound.setAddressType(address.getAddressType());
                    addressFound.setStreetAddress(address.getStreetAddress());
                    addressFound.setCity(address.getCity());
                    addressFound.setCountry(address.getCountry());
                    return addressRepository.save(addressFound);
                })
                .orElseGet(() -> {
                    address.setId(id);
                    return addressRepository.save(address);
                });
    }


    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id){
        addressRepository.deleteById(id);
    }
}