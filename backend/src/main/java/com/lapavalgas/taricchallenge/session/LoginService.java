package com.lapavalgas.taricchallenge.session;

import com.lapavalgas.taricchallenge.clienteDomain.entities.DTO;
import com.lapavalgas.taricchallenge.clienteDomain.entities.MSG;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class LoginService {
    @SchemaMapping(typeName = "Mutation", value = "login")
    public static DTO login(@Argument String user, @Argument String pass) {
        var dto = new DTO();
        if (!FakeSession.isUserLogged()) {
            if (FakeSession.isUserCredentialsValid(user, pass)) {
                FakeSession.USER_LOGGED = (user + pass);
                dto.setCookie(user + pass);
                return dto.setHttpResponse(MSG.STATUS_CODE_200, MSG.USER_LOGGED);
            }
        } else {
            return dto.setHttpResponse(MSG.STATUS_CODE_400, MSG.USER_ALREADY_LOGGED);
        }
        return dto.setHttpResponse(MSG.STATUS_CODE_400, MSG.USER_LOGIN_FAILED);
    }

    @SchemaMapping(typeName = "Query", value = "logoff")
    public static DTO logoff() {
        FakeSession.logoff();
        var dto = new DTO();
        return dto.setHttpResponse(MSG.STATUS_CODE_200, MSG.USER_LOGOFF);
    }
}
