package com.lapavalgas.taricchallenge.clienteDomain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn(name = "cliente_id")
    private Contato contato;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn(name = "cliente_id")
    private Endereco Endereco;

    private String msg;
    private String statusCode;
    public Cliente setHttpResponse(String statusCode, String msg) {
        MSG.log(statusCode + " | MSG: " + msg);
        this.statusCode = statusCode;
        this.msg = msg;
        return this;
    }

}
