package com.lapavalgas.showcase.domain.service;

import com.lapavalgas.showcase.domain.MSG;

public class FakeSessionService {
    private static String ADMIN_USER = "admin";
    private static String ADMIN_PASSWORD = "admin";
    public static String USER_LOGGED = ""; // deixar em branco // 

    public static boolean isUserCredentialsValid(String user, String password) {
        if (ADMIN_USER.equals(user) && ADMIN_PASSWORD.equals(password)) {
            MSG.log(MSG.USER_LOGIN_SUCCESS);
            return true;
        }
        MSG.log(MSG.USER_LOGIN_FAILED);
        return false;
    }

    public static boolean isUserLogged() {
        if (USER_LOGGED.isEmpty()) {
            MSG.log(MSG.USER_NOT_LOGGED);
            return false;
        }
        MSG.log(MSG.USER_LOGGED);
        return true;
    }

    public static void logoff() {
        USER_LOGGED = "";
    }

}
