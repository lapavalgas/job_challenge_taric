package com.lapavalgas.taricchallenge.clienteDomain.services;

import com.lapavalgas.taricchallenge.clienteDomain.entities.Cliente;
import com.lapavalgas.taricchallenge.clienteDomain.entities.DTO;
import com.lapavalgas.taricchallenge.clienteDomain.entities.MSG;
import com.lapavalgas.taricchallenge.clienteDomain.entities.Mapper;
import com.lapavalgas.taricchallenge.clienteDomain.repositories.ClienteRepository;
import com.lapavalgas.taricchallenge.clienteDomain.repositories.EnderecoCEPRepository;
import com.lapavalgas.taricchallenge.session.FakeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EnderecoCEPRepository enderecoCEPRepository;

    @SchemaMapping(typeName = "Query", value = "clientes")
    public DTO clientes() {
        if (!FakeSession.isUserLogged()) {
            var dto = Mapper.clienteListToDTOListFullParse(clienteRepository.findAll());
            return dto.setHttpResponse(MSG.STATUS_CODE_200, MSG.CUSTOMER_SUCCESS_TO_LOAD);
        }
        var dto = new DTO();
        return dto.setHttpResponse(MSG.STATUS_CODE_400, MSG.USER_LOGOFF);
    }

    @SchemaMapping(typeName = "Query", value = "cliente")
    public DTO cliente(@Argument Long id) {
        if (!FakeSession.isUserLogged()) {
            try {
                var dto = Mapper.clienteToDTOFullParse(clienteRepository.findById(id).get());
                return dto.setHttpResponse(MSG.STATUS_CODE_200, MSG.CUSTOMER_SUCCESS_TO_LOAD);
            } catch (Exception e) {
                var dto = new DTO();
                return dto.setHttpResponse(MSG.STATUS_CODE_404, MSG.CUSTOMER_NOT_FOUND);
            }
        }
        var dto = new DTO();
        return dto.setHttpResponse(MSG.STATUS_CODE_400, MSG.USER_LOGOFF);
    }

    private Cliente saveClienteToggle(Cliente cliente) {
        var cep = cliente.getEndereco().getEnderecoCep().getCep();
        try {
            var enderecoCepInOurDataBase = enderecoCEPRepository.findByCep(cep);
            if (enderecoCepInOurDataBase == null) {
                throw new Exception(MSG.CEP_NOT_PRESENT_IN_OUR_DATABASE);
            }
            cliente.getEndereco().setEnderecoCep(enderecoCepInOurDataBase);
        } catch (Exception e) {
            cliente.getEndereco().getEnderecoCep().setId(null);
        } finally {
            cliente = clienteRepository.save(cliente);
        }
        return cliente;
    }

    @SchemaMapping(typeName = "Mutation", value = "saveCliente")
    public DTO saveCliente(@Argument DTO data) {
        if (!FakeSession.isUserLogged()) {
            var savedCliente = saveClienteToggle(Mapper.dtoToClienteFullParse(data));
            var dto = Mapper.clienteToDTOFullParse(savedCliente);
            return dto.setHttpResponse(MSG.STATUS_CODE_200, MSG.CUSTOMER_SUCCESS_TO_SAVE);
        }
        return data.setHttpResponse(MSG.STATUS_CODE_400, MSG.USER_LOGOFF);
    }

    @SchemaMapping(typeName = "Mutation", value = "deleteCliente")
    public DTO deleteCliente(@Argument Long id) {
        var dto = new DTO();
        if (!FakeSession.isUserLogged()) {
            try {

                clienteRepository.deleteById(id);
                return dto.setHttpResponse(MSG.STATUS_CODE_200, MSG.CUSTOMER_SUCCESS_TO_DELETE);
            } catch (Exception e) {
                return dto.setHttpResponse(MSG.STATUS_CODE_404, MSG.CUSTOMER_NOT_FOUND);
            }
        }
        return dto.setHttpResponse(MSG.STATUS_CODE_400, MSG.USER_LOGOFF);
    }

    @SchemaMapping(typeName = "Query", value = "enderecosCep")
    public DTO enderecosCep() {
        if (!FakeSession.isUserLogged()) {
            var dto = Mapper.enderecoListToDTOListFullParse(enderecoCEPRepository.findAll());
            return dto.setHttpResponse(MSG.STATUS_CODE_200, MSG.ADDRESS_SUCCESS_TO_LOAD);
        }
        var dto = new DTO();
        return dto.setHttpResponse(MSG.STATUS_CODE_400, MSG.USER_LOGOFF);
    }

}
