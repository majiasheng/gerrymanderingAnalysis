package model;

public class SessionConstant {

    // attribute name in session/response
    public static final String STATE_ATTRIBUTE = "state";
    public static final String USER_ATTRIBUTE = "user";
    public static final String INIT_ATTRIBUTE = "init";
    public static final String MSG_ATTRIBUTE = "msg";

    // messages
    public static final String REG_FORM_ERROR_MSG = "<p style=\"color:red;\">Error in registration form</p>";
    public static final String REG_SUCCESS_MSG = "<p style=\"color:green;\">Registration success</p>";
    public static final String REG_FAILURE_MSG = "<p style=\"color:red;\">Error in registering: failed to add user to database</p>";
    public static final String LOGIN_FAILURE_MSG = "<p style=\"color:red\">Username and password do not match</p>";
    // request params
    public static final String STATE_REQUEST_PARAM = "state";
    public static final String YEAR_REQUEST_PARAM = "year";
    public static final String USERNAME_REQUEST_PARAM = "username";
    public static final String PASSWORD_REQUEST_PARAM = "password";

    // user password length range
    public static final int MIN_PW_LEN = 8;
    public static final int MAX_PW_LEN = 32;

}
