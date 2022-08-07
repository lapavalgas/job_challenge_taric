package com.lapavalgas.taricchallenge.domain.service;

import com.lapavalgas.taricchallenge.domain.DTO;
import com.lapavalgas.taricchallenge.domain.MSG;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class LoginService {

    @SchemaMapping(typeName = "Mutation", value = "login")
    public static DTO login(@Argument String user, @Argument String pass) {
        var dto = new DTO();
        if (!FakeSessionService.isUserLogged()) {
            if (FakeSessionService.isUserCredentialsValid(user, pass)) {
                FakeSessionService.USER_LOGGED = (user + pass);
                dto.setCookie(user + pass);
                return dto.setHttpResponse(MSG.STATUS_CODE_200, MSG.USER_LOGGED);
            }
        } else {
            return dto.setHttpResponse(MSG.STATUS_CODE_406, MSG.USER_ALREADY_LOGGED);
        }
        return dto.setHttpResponse(MSG.STATUS_CODE_400, MSG.USER_LOGIN_FAILED);
    }

    @SchemaMapping(typeName = "Mutation", value = "logoff")
    public static DTO logoff() {
        FakeSessionService.logoff();
        var dto = new DTO();
        return dto.setHttpResponse(MSG.STATUS_CODE_200, MSG.USER_LOGOFF);
    }

}
