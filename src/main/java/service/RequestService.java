package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class RequestService {

    // attribute name for State object in session/request
    public static final String STATE_ATTRIBUTE = "state";
    public static final String USER_ATTRIBUTE = "user";
    public static final String INIT_ATTRIBUTE = "init";

    // request params
    public static final String STATE_REQUEST_PARAM = "state";
    public static final String YEAR_REQUEST_PARAM = "year";
    public static final String USERNAME_REQUEST_PARAM = "username";
    public static final String PASSWORD_REQUEST_PARAM = "password";

    /**
     * Determines if a request is an AJAX request, do nothing if request is AJAX
     * request, redirect to home page otherwise
     *
     * @param request incoming http request
     * @param response http response
     * @return true if the request should be sent home, false otherwise
     */
    public boolean sendHomeIfNotXHR(HttpServletRequest request, HttpServletResponse response) {
        String requestedWith = request.getHeader("X-Requested-With");

        if (!"XMLHttpRequest".equals(requestedWith)) {
            // redirect back to home
            try {
                /* note: callers(controllers) are annotated with @ResponseBody, 
                 the response will be serialized into JSON and passed back to the
                 response object.
                 see:
                 https://stackoverflow.com/questions/36840104/spring-mvc-redirect-in-responsebody
                
                 TODO: need to find another way to get around this
                 */

                response.sendRedirect("/");
                return true;
            } catch (IOException ex) {
                System.err.println("Error: Cannot redirect to home page");
            }
        }
        return false;
    }

}
