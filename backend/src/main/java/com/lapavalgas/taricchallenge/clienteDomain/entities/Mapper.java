package com.lapavalgas.taricchallenge.clienteDomain.entities;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static EnderecoCEP dtoToEnderecoCEP(DTO dto) {
        return EnderecoCEP.builder()
                .id(dto.getEnderecoCepId())
                .cep(dto.getCep())
                .logradouro(dto.getLogradouro())
                .complemento(dto.getComplemento())
                .numero(dto.getNumero())
                .bairro(dto.getBairro())
                .localidade(dto.getLocalidade())
                .uf(dto.getUf())
                .ibge(dto.getIbge())
                .build();
    }

    public static DTO enderecoCEPToDTO(EnderecoCEP enderecoCEP) {
        var dto = DTO.builder()
                .enderecoCepId(enderecoCEP.getId())
                .cep(enderecoCEP.getCep())
                .logradouro(enderecoCEP.getLogradouro())
                .numero(enderecoCEP.getNumero())
                .complemento(enderecoCEP.getComplemento())
                .bairro(enderecoCEP.getBairro())
                .localidade(enderecoCEP.getLocalidade())
                .uf(enderecoCEP.getUf())
                .ibge(enderecoCEP.getIbge())
                .build();
        dto.setClientesAssociados(new ArrayList<>());
        for (Endereco complementoEnderecoCliente : enderecoCEP.getEnderecos()) {
            dto.addData(complementoEnderecoCliente.getId());
        }
        return dto;
    }

    public static Endereco dtoToEndereco(DTO dto) {
        return Endereco.builder()
                .id(dto.getClienteId())
                .complemento(dto.getComplemento())
                .numero(dto.getNumero())
                .build();
    }

    public static DTO enderecoToDTO(Endereco endereco) {
        return DTO.builder()
                .clienteId(endereco.getId())
                .complemento(endereco.getComplemento())
                .numero(endereco.getNumero())
                .build();
    }

    public static Endereco dtoToEnderecoFullParse(DTO dto) {
        var enderecoCep = EnderecoCEP.builder()
                .id(dto.getEnderecoCepId())
                .cep(dto.getCep())
                .logradouro(dto.getLogradouro())
                .numero(dto.getNumero())
                .complemento(dto.getComplemento())
                .bairro(dto.getBairro())
                .localidade(dto.getLocalidade())
                .uf(dto.getUf())
                .ibge(dto.getIbge())
                .build();

        return Endereco.builder()
                .id(dto.getClienteId())
                .enderecoCep(enderecoCep)
                .numero(dto.getNumero())
                .complemento(dto.getComplemento())
                .numero(dto.getNumero())
                .build();
    }

    public static DTO enderecoToDTOFullParse(Endereco endereco) {
        var enderecoCep = endereco.getEnderecoCep();
        return DTO.builder()
                .enderecoCepId(endereco.getId())
                .cep(enderecoCep.getCep())
                .logradouro(enderecoCep.getLogradouro())
                .numero(endereco.getNumero())
                .complemento(enderecoCep.getComplemento())
                .bairro(enderecoCep.getBairro())
                .localidade(enderecoCep.getLocalidade())
                .uf(enderecoCep.getUf())
                .ibge(enderecoCep.getIbge())
                .build();
    }

    public static Contato dtoToContato(DTO dto) {
        return Contato.builder()
                .id(dto.getClienteId())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .build();
    }

    public static DTO contatoToDTO(Contato contato) {
        return DTO.builder()
                .clienteId(contato.getId())
                .email(contato.getEmail())
                .telefone(contato.getTelefone())
                .build();
    }

    public static Cliente dtoToCliente(DTO dto) {
        return Cliente.builder()
                .id(dto.getClienteId())
                .cpf(dto.getCpf())
                .nome(dto.getNome())
                .build();
    }

    public static DTO clienteToDTO(Cliente cliente) {
        return DTO.builder()
                .clienteId(cliente.getId())
                .cpf(cliente.getCpf())
                .nome(cliente.getNome())
                .build();
    }

    public static Cliente dtoToClienteFullParse(DTO dto) {
        var enderecoCep = dtoToEnderecoCEP(dto);
        var endereco = dtoToEndereco(dto);
        var contato = dtoToContato(dto);
        var cliente = dtoToCliente(dto);

        enderecoCep.setEnderecos(new ArrayList<>());

        endereco.setEnderecoCep(enderecoCep);
        endereco.getEnderecoCep().addEndereco(endereco);
        cliente.setEndereco(endereco);
        cliente.getEndereco().setCliente(cliente);

        cliente.setContato(contato);
        cliente.getContato().setCliente(cliente);

        return cliente;
    }

    public static DTO clienteToDTOFullParse(Cliente cliente) {
        return DTO.builder()
                .clienteId(cliente.getId())
                .enderecoCepId(cliente.getEndereco().getEnderecoCep().getId())
                .cpf(cliente.getCpf())
                .nome(cliente.getNome())
                .email(cliente.getContato().getEmail())
                .telefone(cliente.getContato().getTelefone())
                .cep(cliente.getEndereco().getEnderecoCep().getCep())
                .logradouro(cliente.getEndereco().getEnderecoCep().getLogradouro())
                .numero(cliente.getEndereco().getNumero())
                .complemento(cliente.getEndereco().getComplemento())
                .bairro(cliente.getEndereco().getEnderecoCep().getBairro())
                .localidade(cliente.getEndereco().getEnderecoCep().getLocalidade())
                .uf(cliente.getEndereco().getEnderecoCep().getUf())
                .ibge(cliente.getEndereco().getEnderecoCep().getIbge())
                .build();
    }

    public static DTO clienteListToDTOListFullParse(List<Cliente> clientes) {
        var dto = new DTO();
        dto.setData(new ArrayList<>());
        for (Cliente c : clientes) {
            dto.addData(clienteToDTOFullParse(c));
        }
        return dto;
    }

    public static DTO enderecoListToDTOListFullParse(List<EnderecoCEP> enderecos) {
        var dto = new DTO();
        dto.setData(new ArrayList<>());
        for (EnderecoCEP e : enderecos) {
            dto.addData(enderecoCEPToDTO(e));
        }
        return dto;
    }
}
