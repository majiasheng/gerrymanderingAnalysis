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
    public static final String GEO_DATA_ATTRIBUTE = "geoData";
    public static final String DEMOGRAPHIC_DATA_ATTRIBUTE = "demographicData";
    public static final String ELECTION_DATA_ATTRIBUTE = "electionData";

    // messages
    public static final String REG_FORM_ERROR_MSG = "<p style=\"color:red;\">Error in registration form</p>";
    public static final String REG_SUCCESS_MSG = "<p style=\"color:green;\">Registration success</p>";
    public static final String REG_FAILURE_MSG = "<p style=\"color:red;\">Error in registering: failed to add user to database</p>";
    public static final String LOGIN_FAILURE_MSG = "<p style=\"color:red\">Username and password do not match</p>";
    public static final String UPDATE_INFO_SUCCESS_MSG = "<p style=\"color:green\">Updated info successfully.</p>";
    public static final String UPDATE_INFO_FAILURE_MSG = "<p style=\"color:red\">Failed to update info.</p>";
    public static final String WRONG_FILE_FORMAT_MSG = "<p style=\"color:red\">All files should be in .csv format.</p>";
    public static final String FILE_UPLOAD_SUCCESS_MSG = "<p style=\"color:green\">Uploaded file successfully.</p>";
    public static final String FILE_UPLOAD_FAILURE_MSG = "<p style=\"color:red\">Failed to upload file.</p>";
    
    
    // request params
    public static final String STATE_REQUEST_PARAM = "state";
    public static final String YEAR_REQUEST_PARAM = "year";
    public static final String USERNAME_REQUEST_PARAM = "username";
    public static final String PASSWORD_REQUEST_PARAM = "password";
    public static final String FIRSTNAME_REQUEST_PARAM = "firstname";
    public static final String LASTNAME_REQUEST_PARAM = "lastname";
    public static final String ALLOWED_TO_UPLOAD_REQUEST_PARAM = "allowedToUpload";

    // user password length range
    public static final int MIN_PW_LEN = 8;
    public static final int MAX_PW_LEN = 32;

}
