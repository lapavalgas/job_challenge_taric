package com.lapavalgas.taricchallenge.clienteDomain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "contato")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(exclude = {"cliente"})
public class Contato {

    @Id
    @Column(name = "cliente_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties("contato")
    @ToString.Exclude
    private Cliente cliente;
}
