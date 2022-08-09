package com.lapavalgas.taricchallenge.domain.service;

import com.lapavalgas.taricchallenge.domain.DTO;
import com.lapavalgas.taricchallenge.domain.MSG;
import com.lapavalgas.taricchallenge.domain.Mapper;
import com.lapavalgas.taricchallenge.domain.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.Assert;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @BeforeAll
    public static void init() {
        MSG.isToLog(false);
    }

    @AfterEach
    public void after() {
        LoginService.logoff();
    }

    public void authentication() {
        LoginService.login("taric", "taric1234");
    }

    @Test
    void check_clientes_api_authentication() {
        var dto = customerService.clientes();
        Assert.isTrue(
                dto.getStatusCode().equals("400"),
                "TEST ERROR - 'clientes api' failed to check authentication");
    }

    @Test
    void check_cliente_api_authentication() {
        var dto = customerService.cliente(getValidIdMock());
        Assert.isTrue(
                dto.getStatusCode().equals("400"),
                "TEST ERROR - 'cliente api' failed to check authentication");
    }

    @Test
    void check_salvaCliente_api_authentication() {
        var dto = customerService.salvaCliente(getAnyDTOMock());
        Assert.isTrue(
                dto.getStatusCode().equals("400"),
                "TEST ERROR - 'cliente api' failed to check authentication");
    }

    @Test
    void check_deleteCliente_api_authentication() {
        var dto = customerService.deleteCliente(getValidIdMock());
        Assert.isTrue(
                dto.getStatusCode().equals("400"),
                "TEST ERROR - 'cliente api' failed to check authentication");
    }

    @Test
    void check_clientes_api_200() {
        authentication();
        var dto = customerService.clientes();
        Assert.isTrue(
                dto.getStatusCode().equals("200"),
                "TEST ERROR - 'clientes api' failed to fetch data of customers");
    }

    @Test
    void check_cliente_api_200() {
        authentication();
        var dto = customerService.cliente(getValidIdMock());
        Assert.isTrue(
                dto.getStatusCode().equals("200"),
                "TEST ERROR - 'cliente api' failed to fetch data of single customer");
    }

    @Test
    void check_cliente_api_404() {
        authentication();
        var dto = customerService.cliente(getInvalidIdMock());
        Assert.isTrue(
                dto.getStatusCode().equals("404"),
                "TEST ERROR - 'cliente api' failed to fetch data of single customer");
    }


    @Test
    void check_salvaCliente_api_200_newCustomer_newAddress() {
        authentication();
        var dto = customerService.salvaCliente(getDTOMock_newCustomerAddress());
        Assert.isTrue(
                dto.getStatusCode().equals("200"),
                "TEST ERROR - 'salvaCLiente api' failed to fetch data of single customer");
    }

    @Test
    void check_salvaCliente_api_200_newCustomer_existingAddress() {
        authentication();
        var dto = customerService.salvaCliente(getDTOMock_newCustomer_existingAddress());
        Assert.isTrue(
                dto.getStatusCode().equals("200"),
                "TEST ERROR - 'clientes api' failed to fetch data of single customer");
    }

    @Test
    void check_salvaCliente_api_200_existingCustomer_newAddress() {
        authentication();
        var dto = customerService.salvaCliente(getDTOMock_existingCustomer_newAddress());
        Assert.isTrue(
                dto.getStatusCode().equals("401"),
                "TEST ERROR - 'clientes api' failed to fetch data of single customer");
    }

    @Test
    void check_salvaCliente_api_200_existingCustomer_existingAddress() {
        authentication();
        var dto = customerService.salvaCliente(getDTOMock_existingCustomer_existingAddress());
        Assert.isTrue(
                dto.getStatusCode().equals("401"),
                "TEST ERROR - 'clientes api' failed to fetch data of single customer");
    }

    @Test
    void check_deleteCliente_api_200() {
        authentication();
        var dto = customerService.deleteCliente(getValidIdMock());
        Assert.isTrue(
                dto.getStatusCode().equals("200"),
                "TEST ERROR - 'cliente api' failed to check authentication");
    }

    @Test
    void check_deleteCliente_api_404() {
        authentication();
        var dto = customerService.deleteCliente(getInvalidIdMock());
        Assert.isTrue(
                dto.getStatusCode().equals("404"),
                "TEST ERROR - 'cliente api' failed to check authentication");
    }

    DTO getAnyDTOMock() {
        var mock = new DTO();
        mock.setCpf("000.000.000-01");
        mock.setNome("Rafael Genericuser");
        mock.setEmail("genericuser@customer.com");
        mock.setTelefone("(00) 00000-0000");
        mock.setCep("00000-000");
        mock.setLogradouro("Av. Teste Ficção");
        mock.setNumero("N. 100");
        mock.setComplemento("Bloco 1000, Ap. 100");
        mock.setBairro("Carambolas");
        mock.setLocalidade("Ameixeira");
        mock.setUf("df");
        mock.setIbge("10000");
        return mock;
    }

    DTO getDTOMock_newCustomerAddress() {
        var mock = new DTO();
        mock.setCpf("010.010.000-01");
        mock.setNome("Rafael Genericuser");
        mock.setEmail("genericuser@customer.com");
        mock.setTelefone("(00) 00000-0000");
        mock.setCep("01000-101");
        mock.setLogradouro("Av. Teste Ficção");
        mock.setNumero("N. 100");
        mock.setComplemento("Bloco 1000, Ap. 100");
        mock.setBairro("Carambolas");
        mock.setLocalidade("Ameixeira");
        mock.setUf("df");
        mock.setIbge("10000");
        return mock;
    }

    DTO getDTOMock_newCustomer_existingAddress() {
        var mock = new DTO();
        mock.setCpf("010.010.000-01");
        mock.setNome("Rafael Genericuser");
        mock.setEmail("genericuser@customer.com");
        mock.setTelefone("(00) 00000-0000");
        mock.setEnderecoCepId(Long.valueOf(1));
        mock.setCep("88067-140");
        mock.setLogradouro("Av. Teste Ficção");
        mock.setNumero("N. 100");
        mock.setComplemento("Bloco 1000, Ap. 100");
        mock.setBairro("Carambolas");
        mock.setLocalidade("Ameixeira");
        mock.setUf("df");
        mock.setIbge("10000");
        return mock;
    }

    DTO getDTOMock_existingCustomer_newAddress() {
        var mock = new DTO();
        mock.setClienteId(Long.valueOf(1));
        mock.setCpf("033.772.890-97");
        mock.setNome("Rafael Genericuser");
        mock.setEmail("genericuser@customer.com");
        mock.setTelefone("(00) 00000-0000");
        mock.setEnderecoCepId(Long.valueOf(999));
        mock.setCep("00000-100");
        mock.setLogradouro("Av. Teste Ficção");
        mock.setNumero("N. 100");
        mock.setComplemento("Bloco 1000, Ap. 100");
        mock.setBairro("Carambolas");
        mock.setLocalidade("Ameixeira");
        mock.setUf("df");
        mock.setIbge("10000");
        return mock;
    }

    DTO getDTOMock_existingCustomer_existingAddress() {
        var mock = new DTO();
        mock.setClienteId(Long.valueOf(1));
        mock.setCpf("033.772.890-97");
        mock.setNome("Rafael Genericuser");
        mock.setEmail("genericuser@customer.com");
        mock.setTelefone("(00) 00000-0000");
        mock.setEnderecoCepId(Long.valueOf(1));
        mock.setCep("88067-140");
        mock.setLogradouro("Av. Teste Ficção");
        mock.setNumero("N. 100");
        mock.setComplemento("Bloco 1000, Ap. 100");
        mock.setBairro("Carambolas");
        mock.setLocalidade("Ameixeira");
        mock.setUf("df");
        mock.setIbge("10000");
        return mock;
    }

    DTO getDTOMock_invalidCustomer_validAddress() {
        var mock = new DTO();
        mock.setClienteId(Long.valueOf(-31));
        mock.setCpf("000.000.000-00");
        mock.setNome("Rafael Genericuser");
        mock.setEmail("genericuser@customer.com");
        mock.setTelefone("(00) 00000-0000");
        mock.setEnderecoCepId(Long.valueOf(1));
        mock.setCep("00000-000");
        mock.setLogradouro("Av. Teste Ficção");
        mock.setNumero("N. 100");
        mock.setComplemento("Bloco 1000, Ap. 100");
        mock.setBairro("Carambolas");
        mock.setLocalidade("Ameixeira");
        mock.setUf("df");
        mock.setIbge("10000");
        return mock;
    }

    String INVALID_CEP = "INVALID";

    DTO getDTOMock_validCustomer_invalidAddress() {
        var mock = new DTO();
        mock.setClienteId(Long.valueOf(1));
        mock.setCpf("000.000.000-00");
        mock.setNome("Rafael Genericuser");
        mock.setEmail("genericuser@customer.com");
        mock.setTelefone("(00) 00000-0000");
        mock.setEnderecoCepId(Long.valueOf(-31));
        mock.setCep(INVALID_CEP);
        mock.setLogradouro("Av. Teste Ficção");
        mock.setNumero("N. 100");
        mock.setComplemento("Bloco 1000, Ap. 100");
        mock.setBairro("Carambolas");
        mock.setLocalidade("Ameixeira");
        mock.setUf("df");
        mock.setIbge("10000");
        return mock;
    }

    DTO getDTOInitialData() {
        var d1 = new DTO();
        d1.setClienteId(Long.valueOf(1));
        d1.setEnderecoCepId(Long.valueOf(1));
        d1.setCpf("000.000.000-00");
        d1.setNome("Rafael Genericuser");
        d1.setEmail("genericuser@customer.com");
        d1.setTelefone("(00) 00000-0000");
        d1.setCep("00000-000");
        d1.setLogradouro("Av. Ficção");
        d1.setNumero("N. 100");
        d1.setComplemento("Bloco 1000, Ap. 100");
        d1.setBairro("Carambolas");
        d1.setLocalidade("Abacaxi");
        d1.setUf("df");
        d1.setIbge("1000");
        return d1;
    }

    Long getValidIdMock() {
        return Long.valueOf(1);
    }

    Long getInvalidIdMock() {
        return Long.valueOf(999);
    }
}
