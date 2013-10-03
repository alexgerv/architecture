package ca.ulaval.glo4003.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.dao.UserDAO;
import ca.ulaval.glo4003.repository.ExistingUsernameException;
import ca.ulaval.glo4003.repository.UserRepository;

@Controller
public class SignupController {
    
    @Inject 
    private UserRepository repository;
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {

        model.addAttribute("userDAO", new UserDAO());
        return "signup";
    }
    
    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute UserDAO user, Model m) {
        String message = "Successfully created user";
        try {
            repository.addNewUser(user.getUsername());
        } catch (ExistingUsernameException e) {
           message = e.getMessage();
        }
        m.addAttribute("message", message);
        
        return "signup";
    }

}
