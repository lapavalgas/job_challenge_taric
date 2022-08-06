package com.lapavalgas.taricchallenge.services;

import com.lapavalgas.taricchallenge.clienteDomain.entities.DTO;
import com.lapavalgas.taricchallenge.clienteDomain.entities.MSG;
import com.lapavalgas.taricchallenge.clienteDomain.services.EnderecoService;
import com.lapavalgas.taricchallenge.session.LoginService;
import com.lapavalgas.taricchallenge.session.FakeSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

@SpringBootTest
@ContextConfiguration(classes={EnderecoService.class, DTO.class})
public class LoginServiceTest {

    @Autowired
    private EnderecoService enderecoService;

    @BeforeAll
    public static void init() {
        MSG.isToLog(false);
    }

    @AfterEach
    public void after(){ LoginService.logoff(); }
    public DTO authentication(){
        return LoginService.login("taric", "taric1234");
    }
    public DTO failedAuthentication(){
        return LoginService.login("rafael", "rafael");
    }

    public DTO userAuthenticationLogoff(){
        return LoginService.logoff();
    }

    @Test
    void userCredentialsValid(){
        var isValidCredentials = FakeSession.isUserCredentialsValid("rafael", "rafael");
        Assert.isTrue(
                isValidCredentials==false,
                "TEST userCredentialsValid ERROR - ComponentTest - A feature falhou autenticação das credenciais.");
    }

    @Test
    void userCredentialsInvalid(){
        var isValidCredentials = FakeSession.isUserCredentialsValid("taric", "taric1234");
        Assert.isTrue(
                isValidCredentials==true,
                "TEST userCredentialsInvalid ERROR - ComponentTest - A feature falhou autenticação das credenciais.");
    }

    @Test
    void userNotLogged(){
        var dto = failedAuthentication();
        var isUserLogged = FakeSession.isUserLogged();
        Assert.isTrue(
                isUserLogged==false,
                "TEST userNotLogged ERROR - ComponentTest - A feature falhou autenticação.");
    }

    @Test
    void userLogged(){
        var dto = authentication();
        var isUserLogged = FakeSession.isUserLogged();
        Assert.isTrue(
                isUserLogged==true,
                "TEST UserLogged ERROR - ComponentTest - A feature falhou autenticação.");
    }
    @Test
    void userLogoff(){
        var dto = authentication();
        var isUserLogged = FakeSession.isUserLogged();
        Assert.isTrue(
                isUserLogged==true,
                "TEST userLogoff ERROR - ComponentTest - A feature falhou autenticação. RE-validar testes: 'userNotLogged' e 'userLogged'.");
        userAuthenticationLogoff();
        isUserLogged = FakeSession.isUserLogged();
        Assert.isTrue(
                isUserLogged==false,
                "TEST userLogoff ERROR - ComponentTest - A feature falhou no logoff da autenticação.");
    }

    @Test
    void validLoginFailed() throws Exception {
        var dto = failedAuthentication();
        Assert.isTrue(
                dto.getStatusCode() == "400",
                "TEST validLoginFailed ERROR - ServiceTest - A feature falhou autenticação.");
    }

    @Test
    void validLoginSuccess() throws Exception {
        var dto = authentication();
        Assert.isTrue(
                dto.getStatusCode() == "200",
                "TEST validLoginSuccess ERROR - ServiceTest - A feature falhou autenticação.");
    }

}
