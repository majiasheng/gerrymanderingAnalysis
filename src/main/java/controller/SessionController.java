package controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import model.SessionConstant;
import service.data.UserEntityService;

/**
 * Controller for handling browsing session requests (login, logout)
 *
 * @author majiasheng
 */
@Controller
@ControllerAdvice
public class SessionController {

    @Autowired
    UserEntityService userEntityService;

    /**
     * Redirects to home page for usre login
     *
     * @return homepage view and models associated with it
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView redirectLogin() {
        return new ModelAndView("index");
    }

    /**
     * Handles user login
     *
     * @param requestParams
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView handleLogin(@RequestParam Map<String, String> requestParams,
            HttpServletRequest request,
            final RedirectAttributes redirectAttributes) {

        // process login request
        User user = userEntityService.login(requestParams.get(SessionConstant.USERNAME_REQUEST_PARAM),
                requestParams.get(SessionConstant.PASSWORD_REQUEST_PARAM)
        );

        if (user == null) {
            // report login failure
            redirectAttributes.addFlashAttribute(SessionConstant.MSG_ATTRIBUTE, SessionConstant.LOGIN_FAILURE_MSG);
        } else {
            // add user to session
            request.getSession().setAttribute(SessionConstant.USER_ATTRIBUTE, user);
        }

        // redirect to prevent double submission when refreshing page
        return new ModelAndView("redirect:/login");
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String handleLogout(HttpServletRequest request) {
        // destroy session
        request.getSession(false).invalidate();
        return "redirect:/";
    }
}
