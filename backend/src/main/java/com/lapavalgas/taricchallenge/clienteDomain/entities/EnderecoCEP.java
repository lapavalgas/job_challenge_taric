package com.lapavalgas.taricchallenge.clienteDomain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "enderecoCep")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(exclude = {"endereco"})
public class EnderecoCEP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cep")
    private String cep;
    @Column(name = "logradouro")
    private String logradouro;
    @Column(name = "completo")
    private String complemento;
    @Column(name = "numero")
    private String numero;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "localidade")
    private String localidade;
    @Column(name = "uf")
    private String uf;
    @Column(name = "ibge")
    private String ibge;

    @OneToMany(mappedBy = "enderecoCep", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("enderecoCep")
    @ToString.Exclude
    private List<Endereco> enderecos;

    public void addEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }
}
