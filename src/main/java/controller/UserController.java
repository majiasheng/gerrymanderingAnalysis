package controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
            modelAndView.setViewName("/registration");
        } else {
            //TODO: do validation first
            
            // add user to database
            if (userEntityService.addUser(user)) {
                redirectAttributes.addFlashAttribute(SessionConstant.MSG_ATTRIBUTE, SessionConstant.REG_SUCCESS_MSG);
            } else {
                redirectAttributes.addFlashAttribute(SessionConstant.MSG_ATTRIBUTE, SessionConstant.REG_FAILURE_MSG);
            }
        }
        return modelAndView;
    }
}
