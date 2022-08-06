package com.lapavalgas.taricchallenge.services;

import com.lapavalgas.taricchallenge.clienteDomain.entities.DTO;
import com.lapavalgas.taricchallenge.clienteDomain.entities.MSG;
import com.lapavalgas.taricchallenge.clienteDomain.services.EnderecoService;
import com.lapavalgas.taricchallenge.session.LoginService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

@SpringBootTest
@ContextConfiguration(classes={EnderecoService.class, DTO.class})
public class EnderecoServiceTest {

    @Autowired
    private EnderecoService enderecoService;

    @BeforeAll
    public static void init() {
        MSG.isToLog(false);
    }

    public void authentication(){
        LoginService.login("taric", "taric1234");
    }


    @Test
    void valida_addresByCEP_user_not_logged() throws Exception {
        var dto = enderecoService.addressByCEP("88101200");
        Assert.isTrue(
                dto.getStatusCode() == "400",
                "TEST NOT LOGGED ERROR - A feature falhou autenticação.");
    }

    @Test
    void valida_addresByCEP_200() throws Exception {
        authentication();
        var dto = enderecoService.addressByCEP("88101200");
        Assert.isTrue(
                dto.getStatusCode() == "200",
                "TEST 200 ERROR - A feature falhou com valor de cep válido.");
    }

    @Test
    void valida_addresByCEP_404() throws Exception {
        authentication();
        var dto = enderecoService.addressByCEP("--------");
        Assert.isTrue(
                dto.getStatusCode() == "404",
                "TEST 404 ERROR - A feature falhou com valor de cep inválido.");
    }
}
