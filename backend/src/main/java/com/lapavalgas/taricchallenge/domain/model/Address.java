package com.lapavalgas.taricchallenge.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(exclude = {"customer"})
public class Address {

    @Id
    @Column(name = "customer_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cep_id")
    private CEP cep;

    @Column(name = "addressNumber")
    private String addressNumber;

    @Column(name = "addressComplement")
    private String addressComplement;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("address")
    @ToString.Exclude
    private Customer customer;

}
