package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class RequestService {
	
	/**
	 * Determines if a request is an AJAX request
	 * @param request incoming request
	 * @return true if the request is an AJAX request, false otherwise
	 */
	public void sendHomeIfNotXHR(HttpServletRequest request, HttpServletResponse response) {
		String requestedWith = request.getHeader("X-Requested-With");
		
		if(!"XMLHttpRequest".equals(requestedWith)) {
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
