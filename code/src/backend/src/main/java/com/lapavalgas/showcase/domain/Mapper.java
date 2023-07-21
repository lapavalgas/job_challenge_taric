package com.lapavalgas.showcase.domain;

import com.lapavalgas.showcase.domain.model.Address;
import com.lapavalgas.showcase.domain.model.CEP;
import com.lapavalgas.showcase.domain.model.Contact;
import com.lapavalgas.showcase.domain.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static CEP dtoToCEP(DTO dto) {
        return CEP.builder()
                .id(dto.getEnderecoCepId())
                .cep(dto.getCep())
                .logradouro(dto.getLogradouro())
//                .complemento(dto.getComplemento())
//                .numero(dto.getNumero())
                .bairro(dto.getBairro())
                .localidade(dto.getLocalidade())
                .uf(dto.getUf())
                .ibge(dto.getIbge())
                .build();
    }

    public static DTO CEPToDTO(CEP CEP) {
        var dto = DTO.builder()
                .enderecoCepId(CEP.getId())
                .cep(CEP.getCep())
                .logradouro(CEP.getLogradouro())
//                .numero(CEP.getNumero())
//                .complemento(CEP.getComplemento())
                .bairro(CEP.getBairro())
                .localidade(CEP.getLocalidade())
                .uf(CEP.getUf())
                .ibge(CEP.getIbge())
                .build();
        dto.setClientesAssociados(new ArrayList<>());
        for (Address complementoAddressCliente : CEP.getAddresses()) {
            dto.addData(complementoAddressCliente.getId());
        }
        return dto;
    }

    public static Address dtoToAdress(DTO dto) {
        return Address.builder()
                .id(dto.getClienteId())
                .addressComplement(dto.getComplemento())
                .addressNumber(dto.getNumero())
                .build();
    }

    public static DTO addressToDTO(Address address) {
        return DTO.builder()
                .clienteId(address.getId())
                .complemento(address.getAddressComplement())
                .numero(address.getAddressNumber())
                .build();
    }

    public static Address dtoToAddressFullParse(DTO dto) {
        var enderecoCep = CEP.builder()
                .id(dto.getEnderecoCepId())
                .cep(dto.getCep())
                .logradouro(dto.getLogradouro())
//                .numero(dto.getNumero())
//                .complemento(dto.getComplemento())
                .bairro(dto.getBairro())
                .localidade(dto.getLocalidade())
                .uf(dto.getUf())
                .ibge(dto.getIbge())
                .build();
        return Address.builder()
                .id(dto.getClienteId())
                .cep(enderecoCep)
                .addressNumber(dto.getNumero())
                .addressComplement(dto.getComplemento())
                .addressNumber(dto.getNumero())
                .build();
    }

    public static DTO addressToDTOFullParse(Address address) {
        var enderecoCep = address.getCep();
        return DTO.builder()
                .enderecoCepId(address.getId())
                .cep(enderecoCep.getCep())
                .logradouro(enderecoCep.getLogradouro())
                .numero(address.getAddressNumber())
//                .complemento(enderecoCep.getComplemento())
                .bairro(enderecoCep.getBairro())
                .localidade(enderecoCep.getLocalidade())
                .uf(enderecoCep.getUf())
                .ibge(enderecoCep.getIbge())
                .build();
    }

    public static Contact dtoToContact(DTO dto) {
        return Contact.builder()
                .id(dto.getClienteId())
                .email(dto.getEmail())
                .tel(dto.getTelefone())
                .build();
    }

    public static DTO contactToDTO(Contact contact) {
        return DTO.builder()
                .clienteId(contact.getId())
                .email(contact.getEmail())
                .telefone(contact.getTel())
                .build();
    }

    public static Customer dtoToCustomer(DTO dto) {
        return Customer.builder()
                .id(dto.getClienteId())
                .cpf(dto.getCpf())
                .name(dto.getNome())
                .build();
    }

    public static DTO customerToDTO(Customer customer) {
        return DTO.builder()
                .clienteId(customer.getId())
                .cpf(customer.getCpf())
                .nome(customer.getName())
                .build();
    }

    public static Customer dtoToCustomerFullParse(DTO dto) {
        var cep = dtoToCEP(dto);
        var address = dtoToAdress(dto);
        var contact = dtoToContact(dto);
        var customer = dtoToCustomer(dto);

        cep.setAddresses(new ArrayList<>());

        address.setCep(cep);
        address.getCep().addressAssociate(address);
        customer.setAddress(address);
        customer.getAddress().setCustomer(customer);

        customer.setContact(contact);
        customer.getContact().setCustomer(customer);

        return customer;
    }

    public static DTO customerToDTOFullParse(Customer customer) {
        return DTO.builder()
                .clienteId(customer.getId())
                .enderecoCepId(customer.getAddress().getCep().getId())
                .cpf(customer.getCpf())
                .nome(customer.getName())
                .email(customer.getContact().getEmail())
                .telefone(customer.getContact().getTel())
                .cep(customer.getAddress().getCep().getCep())
                .logradouro(customer.getAddress().getCep().getLogradouro())
                .numero(customer.getAddress().getAddressNumber())
                .complemento(customer.getAddress().getAddressComplement())
                .bairro(customer.getAddress().getCep().getBairro())
                .localidade(customer.getAddress().getCep().getLocalidade())
                .uf(customer.getAddress().getCep().getUf())
                .ibge(customer.getAddress().getCep().getIbge())
                .build();
    }

    public static DTO customerListToDTOListFullParse(List<Customer> customers) {
        var dto = new DTO();
        dto.setData(new ArrayList<>());
        for (Customer c : customers) {
            dto.addData(customerToDTOFullParse(c));
        }
        return dto;
    }

    public static DTO addressListToDTOListFullParse(List<CEP> address) {
        var dto = new DTO();
        dto.setData(new ArrayList<>());
        for (CEP e : address) {
            dto.addData(CEPToDTO(e));
        }
        return dto;
    }

}
