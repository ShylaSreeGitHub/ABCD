package in.projects.loginandregistration.web;

import in.projects.loginandregistration.Model.User;
import org.springframework.boot.Banner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
//        return "login";
        return modelAndView;
    }
    @GetMapping("/")
    public ModelAndView homePage(){
        return new ModelAndView("index");
    }
}
