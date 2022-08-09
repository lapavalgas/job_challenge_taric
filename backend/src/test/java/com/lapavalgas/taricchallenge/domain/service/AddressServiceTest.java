package com.lapavalgas.taricchallenge.domain.service;

import com.lapavalgas.taricchallenge.domain.DTO;
import com.lapavalgas.taricchallenge.domain.MSG;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AddressServiceTest {

    @Autowired
    AddressService addressService;

    @BeforeAll
    public static void init() {
        MSG.isToLog(false);
    }

    @AfterEach
    public void after(){ LoginService.logoff(); }

    public void authentication() {
        LoginService.login("taric", "taric1234");
    }

    @Test
    void check_addressByCEP_api_authentication() {
        var dto = addressService.buscaCep("88101-200");
        Assert.isTrue(
                dto.getStatusCode().equals("400"),
                "TEST ERROR - 'addressByCEP api' failed to check authentication");
    }

    @Test
    void check_existent_addressByCEP_api_200() {
        authentication();
        var dto = addressService.buscaCep("88067-140");
        Assert.isTrue(
                dto.getStatusCode().equals("200"),
                "TEST ERROR - 'addressByCEP api' failed to check an existent address value");
    }
    @Test
    void check_addressByCEP_api_200() {
        authentication();
        var dto = addressService.buscaCep("88101200");
        Assert.isTrue(
                dto.getStatusCode().equals("200"),
                "TEST ERROR - 'addressByCEP api' failed to check OpenCEP with valid value");
    }

    @Test
    void check_addressByCEP_api_404() {
        authentication();
        var dto = addressService.buscaCep("--------");
        Assert.isTrue(
                dto.getStatusCode().equals("404"),
                "TEST ERROR - 'addressByCEP api' failed to check OpenCEP with invalid value");
    }

    @Test
    void check_addressBy_api_200() {
        authentication();
        var dto = addressService.enderecosCep();
        Assert.isTrue(
                dto.getStatusCode().equals("200"),
                "TEST ERROR - 'addressByCEP api' failed to retrieve the cep list");
    }

    @Test
    void check_addressBy_api_400() {
        var dto = addressService.enderecosCep();
        Assert.isTrue(
                dto.getStatusCode().equals("400"),
                "TEST ERROR - 'addressByCEP api' failed to retrieve the cep list");
    }
}
