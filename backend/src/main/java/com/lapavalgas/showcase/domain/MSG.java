package com.lapavalgas.showcase.domain;

public class MSG {

    //    AUTHENTICATION
    public static String USER_LOGIN_SUCCESS = "User login success";
    public static String USER_LOGIN_FAILED = "User login failed";
    public static String USER_LOGGED = "User logged";
    public static String USER_ALREADY_LOGGED = "User already logged";
    public static String USER_NOT_LOGGED = "User not logged";
    public static String USER_LOGOFF = "User logout";

    //    CEP VALIDATION
    public static final String OPEN_CEP_VALID = "The ZIP code is valid by OpenCEP";
    public static final String OPEN_CEP_INVALID = "The ZIP code is invalid";
    public static final String CEP_ALREADY_IN_OUR_DATABASE = "The ZIP code exist in our database";
    public static final String CEP_NOT_PRESENT_IN_OUR_DATABASE = "The ZIP code doesn't exist in our database";

    //    CLIENTE API
    public static final String CUSTOMER_SUCCESS_TO_SAVE = "Customer data successfully saved";
    public static final String CUSTOMER_SUCCESS_TO_LOAD = "Customer data successfully loaded";
    public static final String CUSTOMER_SUCCESS_TO_DELETE = "Customer data successfully deleted";
    public static final String CUSTOMER_NOT_FOUND = "Customer not found in our database";

    public static final String CPF_ALREADY_IN_DATABASE = "Customer CPF found in database ";

    //    ENDERECO API
    public static final String ADDRESS_SUCCESS_TO_LOAD = "Address data successfully loaded";

    //    STATUS CODE
    public static final String STATUS_CODE_200 = "200";
    public static final String STATUS_CODE_400 = "400";
    public static final String STATUS_CODE_401 = "401";
    public static final String STATUS_CODE_404 = "404";
    public static final String STATUS_CODE_406 = "406";

    public static final String STATUS_CODE_500 = "500";

    //    LOG
    public static Boolean isToLog = true;

    public static void isToLog(boolean value) {
        isToLog = value;
    }

    public static void log(String log) { if (MSG.isToLog) { System.out.println("LOG: " + log); }
    }

}
