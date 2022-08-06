package com.lapavalgas.taricchallenge.clienteDomain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "endereco")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(exclude = { "cliente" })
public class Endereco {
    @Id
    @Column(name = "cliente_id")
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "enderecoCep_id")
    private EnderecoCEP enderecoCep;
    @Column(name = "numero")
    private String numero;
    @Column(name = "complemento")
    private String complemento;
    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties("endereco")
    @ToString.Exclude
    private Cliente cliente;
}
