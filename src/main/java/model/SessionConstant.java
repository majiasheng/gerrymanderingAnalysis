package model;

/**
 * Constants
 * @author majiasheng
 */
public class SessionConstant {

    // attribute name in session/response
    public static final String STATE_ATTRIBUTE = "state";
    public static final String USER_ATTRIBUTE = "user";
    public static final String CONFIG_ATTRIBUTE = "config";
    public static final String MSG_ATTRIBUTE = "msg";
    public static final String NORMAL_USER_ATTRIBUTE = "normalUsers";

    // messages
    public static final String REG_FORM_ERROR_MSG = "<div class='alert alert-danger'>Error in registration form</p></div>";
    public static final String REG_SUCCESS_MSG = "<div class='alert alert-success'>Registration success</div>";
    public static final String REG_FAILURE_MSG = "<div class='alert alert-danger'>Error in registering: failed to add user to database</div>";
    public static final String LOGIN_FAILURE_MSG = "<div class='alert alert-danger'>Username and password do not match</div>";

    // request params
    public static final String STATE_REQUEST_PARAM = "state";
    public static final String YEAR_REQUEST_PARAM = "year";
    public static final String USERNAME_REQUEST_PARAM = "username";
    public static final String PASSWORD_REQUEST_PARAM = "password";

    // user password length range
    public static final int MIN_PW_LEN = 8;
    public static final int MAX_PW_LEN = 32;

}
