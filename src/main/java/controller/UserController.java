package controller;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
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
import model.SessionConstant;
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
     * @param requestParams
     * @param result
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView handleRegistration(
            @Valid @ModelAttribute(SessionConstant.USER_ATTRIBUTE) User user,
            BindingResult result,
            @RequestParam Map<String, String> requestParams,
            final RedirectAttributes redirectAttributes) {

        // redirect to prevent double submission when refreshing page
        ModelAndView modelAndView = new ModelAndView("redirect:/register");

        if (result.hasErrors()) {
            // go back to regitration page if form has errors
            modelAndView.setViewName("/registration");
        } else {
            // add user to database
            if (userEntityService.addUser(user)) {
                // report success
                redirectAttributes.addFlashAttribute(SessionConstant.MSG_ATTRIBUTE, SessionConstant.REG_SUCCESS_MSG);
            } else {
                // report failure
                redirectAttributes.addFlashAttribute(SessionConstant.MSG_ATTRIBUTE, SessionConstant.REG_FAILURE_MSG);
            }
        }
        return modelAndView;
    }

    /**
     * Retrieves a list of normal users from the database, and adds the list to
     * the admin's "manage page"
     *
     * @return
     */
    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public ModelAndView manageNormalUser() {

        ModelAndView mv = new ModelAndView("manage");
        Collection<User> normalUsers = userEntityService.getAllNormalUsers();
        mv.addObject(SessionConstant.NORMAL_USER_ATTRIBUTE, normalUsers);
        return mv;
    }

}
