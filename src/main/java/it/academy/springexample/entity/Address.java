package it.academy.springexample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Address {
    @Id
    //definisce il proprio id alla tabella (senza usa un id per tutte le tabelle)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull
    private String streetAddress;
    @NotNull
    private String city;

    @NotNull
    private String zipCode;

    @NotNull
    private String country;

    @NotNull
    private String addressType;

// ASSOCIA TUTTI GLI INDIRIZZI AI RELATIVI CUSTOMERS
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
