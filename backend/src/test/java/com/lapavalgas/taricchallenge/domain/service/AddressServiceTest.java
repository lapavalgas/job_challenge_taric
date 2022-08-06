package com.lapavalgas.taricchallenge.domain.service;

import com.lapavalgas.taricchallenge.domain.DTO;
import com.lapavalgas.taricchallenge.domain.MSG;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

@SpringBootTest
//@ContextConfiguration(classes = {AddressService.class, DTO.class})
public class AddressServiceTest {

    @Autowired
    AddressService addressService;

    @BeforeAll
    public static void init() {
        MSG.isToLog(false);
    }

    public void authentication() {
        LoginService.login("taric", "taric1234");
    }

    @Test
    void check_addressByCEP_api_authentication() {
        var dto = addressService.buscaCep("88101200");
        Assert.isTrue(
                dto.getStatusCode().equals("400"),
                "TEST ERROR - addressByCEP api failed to check authentication");
    }

    @Test
    void check_addressByCEP_200() {
        authentication();
        var dto = addressService.buscaCep("88101200");
        Assert.isTrue(
                dto.getStatusCode().equals("200"),
                "TEST ERROR - addressByCEP api failed to check OpenCEP with valid value");
    }

    @Test
    void check_addressByCEP_404() {
        authentication();
        var dto = addressService.buscaCep("--------");
        Assert.isTrue(
                dto.getStatusCode().equals("404"),
                "TEST ERROR - addressByCEP api failed to check OpenCEP with invalid value");
    }
}
