package controller;

import java.util.Collection;
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
        //TODO: change manage to admin

        ModelAndView mv = new ModelAndView("manage");
        Collection<User> normalUsers = userEntityService.getAllNormalUsers();
        mv.addObject(SessionConstant.NORMAL_USER_ATTRIBUTE, normalUsers);
        return mv;
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ModelAndView manageAccountSettings() {
        ModelAndView mv = new ModelAndView("user-setting");
        return mv;
    }

    @RequestMapping(value = "/confirmEdit", method = RequestMethod.POST)
    public ModelAndView confirmEdit(@RequestParam Map<String, String> requestParams,
            HttpServletRequest request,
            final RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        // get user from session
        User user = (User) request.getSession().getAttribute(SessionConstant.USER_ATTRIBUTE);
        // update user 
        user.setFirstName(requestParams.get(SessionConstant.FIRSTNAME_REQUEST_PARAM));
        user.setLastName(requestParams.get(SessionConstant.LASTNAME_REQUEST_PARAM));

        if (userEntityService.updateUser(user)) {
            mv = new ModelAndView("redirect:/confirmEdit");
            redirectAttributes.addFlashAttribute(SessionConstant.MSG_ATTRIBUTE, SessionConstant.UPDATE_INFO_SUCCESS_MSG);
        } else {
            // send user back to user setting page with error message 
            mv.setViewName(request.getRequestURI());
            redirectAttributes.addFlashAttribute(SessionConstant.MSG_ATTRIBUTE, SessionConstant.UPDATE_INFO_FAILURE_MSG);
        }
        return mv;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/confirmEdit", method = RequestMethod.GET)
    public ModelAndView redirectConfirmEdit() {
        ModelAndView mv = new ModelAndView("user-setting");
        return mv;
    }
    
    @RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
    public ModelAndView adminDeleteUser(@RequestParam(SessionConstant.USERNAME_REQUEST_PARAM) String username, HttpServletRequest request) {
        
        ModelAndView mv = new ModelAndView("/manage");
        
        if (userEntityService.deleteUser(username)) {
            mv.addObject("msg", "<p style=\"color:green\">Successfully deleted " + username + " from database</p>");
        } else {
            mv.addObject("msg", "<p style=\"color:red\">Fail to delete " + username + " from database</p>");
        }
        return mv;
        
    }
    
    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public void adminEditUser(@RequestParam Map<String, String> requestParams, HttpServletRequest request) {
        for(String k : requestParams.keySet()) {
            System.out.println(k + ": " + requestParams.get(k));
        }
    }
}
