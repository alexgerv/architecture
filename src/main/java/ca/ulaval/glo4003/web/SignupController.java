package ca.ulaval.glo4003.web;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.repository.ExistingUsernameException;
import ca.ulaval.glo4003.repository.UserRepository;
import ca.ulaval.glo4003.web.viewmodels.UserViewModel;

@Controller
public class SignupController {

    @Autowired
    Md5PasswordEncoder passwordEncoder;

    @Inject
    UserRepository userRepository;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {

        model.addAttribute("userDAO", new UserViewModel());
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute UserViewModel user, Model m) {
        String message = "Successfully created user";
        try {
            userRepository.addNewUser(user.getUsername(), hashPassword(user.getPassword()), 0);
        } catch (ExistingUsernameException e) {
            message = e.getMessage();
        }
        m.addAttribute("message", message);

        return "login";
    }

    private String hashPassword(String password) {
        return passwordEncoder.encodePassword(password, null);
    }

    protected SignupController(UserRepository repository, Md5PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = new Md5PasswordEncoder();
    }

}
