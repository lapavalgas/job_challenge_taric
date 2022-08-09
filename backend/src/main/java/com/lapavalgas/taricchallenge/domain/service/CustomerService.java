package com.lapavalgas.taricchallenge.domain.service;

import com.lapavalgas.taricchallenge.domain.model.Customer;
import com.lapavalgas.taricchallenge.domain.DTO;
import com.lapavalgas.taricchallenge.domain.MSG;
import com.lapavalgas.taricchallenge.domain.Mapper;
import com.lapavalgas.taricchallenge.domain.repository.CEPRepository;
import com.lapavalgas.taricchallenge.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;

@Controller
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CEPRepository cepRepository;

    @SchemaMapping(typeName = "Query", value = "clientes")
    public DTO clientes() {
        if (FakeSessionService.isUserLogged()) {
            var dto = Mapper.customerListToDTOListFullParse(customerRepository.findAll());
            return dto.setHttpResponse(MSG.STATUS_CODE_200, MSG.CUSTOMER_SUCCESS_TO_LOAD);
        }
        var dto = new DTO();
        return dto.setHttpResponse(MSG.STATUS_CODE_400, MSG.USER_LOGOFF);
    }

    @SchemaMapping(typeName = "Query", value = "cliente")
    public DTO cliente(@Argument Long id) {
        if (FakeSessionService.isUserLogged()) {
            try {
                var dto = Mapper.customerToDTOFullParse(customerRepository.findById(id).get());
                return dto.setHttpResponse(MSG.STATUS_CODE_200, MSG.CUSTOMER_SUCCESS_TO_LOAD);
            } catch (Exception e) {
                var dto = new DTO();
                return dto.setHttpResponse(MSG.STATUS_CODE_404, MSG.CUSTOMER_NOT_FOUND);
            }
        }
        var dto = new DTO();
        return dto.setHttpResponse(MSG.STATUS_CODE_400, MSG.USER_LOGOFF);
    }

    private Customer salvaClienteLogic(Customer customer) throws Exception {
        var cep = customer.getAddress().getCep().getCep();
        try {
            var enderecoCepInOurDataBase = cepRepository.findByCep(cep);
            if (enderecoCepInOurDataBase.isEmpty()) {
                throw new Exception(MSG.CEP_NOT_PRESENT_IN_OUR_DATABASE);
            }
            customer.getAddress().setCep(enderecoCepInOurDataBase.get());
        } catch (Exception e) {
            customer.getAddress().getCep().setId(null);
        } finally {
            var isTheCpfAlreadyIn = customerRepository.findByCpf(customer.getCpf());
            if (isTheCpfAlreadyIn.isEmpty() || isTheCpfAlreadyIn.get().getId().equals(customer.getId())) {
                customer = customerRepository.save(customer);
            } else {
                throw new Exception(MSG.CPF_ALREADY_IN_DATABASE);
            }
        }
        return customer;
    }

    @Transactional
    @SchemaMapping(typeName = "Mutation", value = "salvaCliente")
    public DTO salvaCliente(@Argument DTO data) {
        if (FakeSessionService.isUserLogged()) {
            try {
                var savedCliente = salvaClienteLogic(Mapper.dtoToCustomerFullParse(data));
                var dto = Mapper.customerToDTOFullParse(savedCliente);
                return dto.setHttpResponse(MSG.STATUS_CODE_200, MSG.CUSTOMER_SUCCESS_TO_SAVE);
            } catch (Exception e) {
                var dto = new DTO();
                return dto.setHttpResponse(MSG.STATUS_CODE_401, e.getMessage());
            }
        }
        return data.setHttpResponse(MSG.STATUS_CODE_400, MSG.USER_LOGOFF);
    }

    @SchemaMapping(typeName = "Mutation", value = "deleteCliente")
    public DTO deleteCliente(@Argument Long id) {
        var dto = new DTO();
        if (FakeSessionService.isUserLogged()) {
            try {
                customerRepository.deleteById(id);
                return dto.setHttpResponse(MSG.STATUS_CODE_200, MSG.CUSTOMER_SUCCESS_TO_DELETE);
            } catch (Exception e) {
                return dto.setHttpResponse(MSG.STATUS_CODE_404, MSG.CUSTOMER_NOT_FOUND);
            }
        }
        return dto.setHttpResponse(MSG.STATUS_CODE_400, MSG.USER_LOGOFF);
    }

}
