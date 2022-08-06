package com.lapavalgas.taricchallenge.session;

import com.lapavalgas.taricchallenge.clienteDomain.entities.MSG;

public class FakeSession {
    private static String ADMIN_USER = "taric";
    private static String ADMIN_PASSWORD = "taric1234";
    public static String USER_LOGGED = "";

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
