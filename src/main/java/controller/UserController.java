package controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.data.UserEntityService;

/**
 *
 * @author majiasheng
 */
@Controller
@ControllerAdvice
public class UserController {

    @Autowired
    UserEntityService userEntityService;

    /**
     * PRG - G
     *
     * @return @see handleRegistration
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    private ModelAndView redirectRegistration() {
        return new ModelAndView("index");
    }

    /**
     * Post,Redirect,Get(PRG) - P,R -- handles user registration form
     *
     * @param user
     * @param result
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView handleRegistration(
            @ModelAttribute("user") User user,
            @RequestParam Map<String, String> requestParams,
            BindingResult result,
            final RedirectAttributes redirectAttributes) {

        /*TODO: check email and password, validate all fields, 
            redirect back to registration.jsp if error, set up ids for error message */
        
        // redirect to prevent double submission when refreshing page
        ModelAndView modelAndView = new ModelAndView("redirect:/register");

        System.out.println("\n\n\nDEBUG: \n" + user.toString() + result.toString() + "\n\n\n");
        
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("msg", "Error in registration form");
        } else {
            // add user to database
            if (userEntityService.addUser(user)) {
                redirectAttributes.addFlashAttribute("msg", "<p style=\"color:green;\">Registration success</p>");
            } else {
                redirectAttributes.addFlashAttribute("msg", "<p style=\"color:red;\">Error in registering: failed to add user to database</p>");
            }
        }
        return modelAndView;
    }
}
