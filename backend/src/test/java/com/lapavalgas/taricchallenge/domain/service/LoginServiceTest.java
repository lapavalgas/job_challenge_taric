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
public class LoginServiceTest {

    @Autowired
    private AddressService addressService;

    @BeforeAll
    public static void init() {
        MSG.isToLog(false);
    }

    @AfterEach
    public void after(){ LoginService.logoff(); }
    public DTO authentication(){
        return LoginService.login("admin", "admin");
    }
    public DTO failedAuthentication(){
        return LoginService.login("rafael", "rafael");
    }

    public DTO userAuthenticationLogoff(){
        return LoginService.logoff();
    }

    @Test
    void check_login_component_validCredentials(){
        var isValidCredentials = FakeSessionService.isUserCredentialsValid("rafael", "rafael");
        Assert.isTrue(
                isValidCredentials==false,
                "TEST ERROR - 'login component' failed to check valid credentials");
    }

    @Test
    void check_login_component_invalidCredentials(){
        var isValidCredentials = FakeSessionService.isUserCredentialsValid("admin", "admin");
        Assert.isTrue(
                isValidCredentials==true,
                "TEST ERROR - 'login component' failed to check invalid credentials");
    }

    @Test
    void check_login_component_userNotLogged(){
        var dto = failedAuthentication();
        var isUserLogged = FakeSessionService.isUserLogged();
        Assert.isTrue(
                isUserLogged==false,
                "TEST ERROR - 'login component' failed to check authentication");
    }

    @Test
    void check_login_component_userLogged(){
        var dto = authentication();
        var isUserLogged = FakeSessionService.isUserLogged();
        Assert.isTrue(
                isUserLogged==true,
                "TEST ERROR - 'login component' failed to check authentication");
    }
    @Test
    void check_login_component_userLogoff(){
        var dto = authentication();
        var isUserLogged = FakeSessionService.isUserLogged();
        Assert.isTrue(
                isUserLogged==true,
                "TEST ERROR - 'login component' failed to check authentication");
        userAuthenticationLogoff();
        isUserLogged = FakeSessionService.isUserLogged();
        Assert.isTrue(
                isUserLogged==false,
                "TEST ERROR - 'login component' failed to check user logoff");
    }

    @Test
    void check_login_api_loginFailed() throws Exception {
        var dto = failedAuthentication();
        Assert.isTrue(
                dto.getStatusCode() == "400",
                "TEST ERROR - 'login api' failed to check failed authentication");
    }

    @Test
    void check_login_api_loginSuccess() throws Exception {
        var dto = authentication();
        Assert.isTrue(
                dto.getStatusCode() == "200",
                "TEST ERROR - 'login api' failed to check success authentication");
    }
    @Test
    void check_login_api_userAlreadyLogged() throws Exception {
        authentication();
        var dto = authentication();
        Assert.isTrue(
                dto.getStatusCode() == "406",
                "TEST ERROR - 'login api' failed to check success authentication");
    }

}
