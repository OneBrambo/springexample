package it.academy.springexample.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="customers")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Customer {
    @Id
    //definisce il proprio id alla tabella (senza usa un id per tutte le tabelle)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String cc;
    @NotNull
    private boolean active;


//ASSOCIA IL SINGOLO CUSTOMER A TUTTI I SUOI INDIRIZZI 1 - n

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

    public List<Address> getAddresses() {
        return addresses;
    }
// ASSOCIA TUTTI I PRODOTTI COMPRATI AI RELATIVI CUSTOMER CHE HANNO COMPRATO X PRODOTTO m - n
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "customers_products",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))

    private List<Product> boughtProducts;

    public List<Product> getBoughtProducts() {
        return boughtProducts;
    }
}
