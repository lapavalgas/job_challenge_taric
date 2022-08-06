package com.lapavalgas.taricchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * The DTO data must be in Portuguese
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTO {

    private Long clienteId;
    private Long enderecoCepId;
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String statusCode;
    private String msg;
    private String exception;
    private String error;

    private String cookie;

    public DTO setHttpResponse(String statusCode, String msg) {
        MSG.log(statusCode + " | MSG: " + msg);
        this.statusCode = statusCode;
        this.msg = msg;
        return this;
    }

    private List<DTO> data = new ArrayList<>();
    private List<Long> clientesAssociados;

    public void addData(DTO dto) {
        data.add(dto);
    }

    public void addData(Long id) {
        clientesAssociados.add(id);
    }

}
