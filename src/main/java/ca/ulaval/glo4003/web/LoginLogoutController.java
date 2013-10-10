package ca.ulaval.glo4003.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginLogoutController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam(value = "error", required = false) boolean error, ModelMap model) {
        
        if (error) {
            model.put("message", "You have entered an invalid username or password!");
        } else {
            model.put("message", "");
        }
        
        return "login";
    }

    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String getDeniedPage() {
        return "deniedpage";
    }
}