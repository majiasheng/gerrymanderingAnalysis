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
import service.RequestService;
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView redirectLogin() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView handleLogin(@RequestParam Map<String, String> requestParams,
            HttpServletRequest request,
            final RedirectAttributes redirectAttributes) {

        // redirect to prevent double submission when refreshing page
        ModelAndView modelAndView = new ModelAndView("redirect:/login");

        User user = userEntityService.login(
                requestParams.get(RequestService.USERNAME_REQUEST_PARAM),
                requestParams.get(RequestService.PASSWORD_REQUEST_PARAM)
        );

        if (user == null) {
            //TODO: show an popup to indicate username and password mismatch
            redirectAttributes.addFlashAttribute("msg", "Username and password do not match");
        } else {
            // add user to session
            request.getSession().setAttribute(RequestService.USER_ATTRIBUTE, user);
        }
        return modelAndView;
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView handleLogout(HttpServletRequest request) {
        // destroy session
        request.getSession(false).invalidate();
        return new ModelAndView("index");
    }
}
