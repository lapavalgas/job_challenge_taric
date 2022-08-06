package com.lapavalgas.taricchallenge.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Brazilian ZIP Code
 */
@Entity
@Table(name = "cep")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(exclude = {"address"})
public class CEP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cep")
    private String cep;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "localidade")
    private String localidade;

    @Column(name = "uf")
    private String uf;

    @Column(name = "ibge")
    private String ibge;

    @OneToMany(mappedBy = "cep", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("cep")
    @ToString.Exclude
    private List<Address> addresses;

    public void addressAssociate(Address address) {
        this.addresses.add(address);
    }
}
