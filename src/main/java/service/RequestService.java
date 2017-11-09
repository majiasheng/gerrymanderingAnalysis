package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class RequestService {

    // attribute name for State object in session/request
    public static final String STATE_ATTRIBUTE = "state";
    
    /**
     * Determines if a request is an AJAX request, do nothing if request is AJAX
     * request, redirect to home page otherwise
     *
     * @param request incoming http request
     * @param response
     */
    public void sendHomeIfNotXHR(HttpServletRequest request, HttpServletResponse response) {
        String requestedWith = request.getHeader("X-Requested-With");

        if (!"XMLHttpRequest".equals(requestedWith)) {
            // redirect back to home
            try {
                //FIXME: send redirect and never let it come back here
                response.sendRedirect("/");
            } catch (IOException ex) {
                System.err.println("Error: Cannot redirect to home page");
            }
        }
    }

}
