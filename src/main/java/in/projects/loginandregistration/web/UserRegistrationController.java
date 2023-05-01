package in.projects.loginandregistration.web;


import in.projects.loginandregistration.Service.UserService;
import in.projects.loginandregistration.web.Dto.UserRegistrationDto;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }
    //or
//    @GetMapping
//    public String showRegistrationForm(Model model){
//        model.addAttribute("user", new UserRegistrationDto() );
//        return "registration";
//    }
    //or
//    @GetMapping
//    public ModelAndView showRegistrationForm(ModelAndView modelAndView){
//        modelAndView.addObject("user", new UserRegistrationDto());
//        modelAndView.setViewName("registration");
//        return modelAndView;
//    }
    @GetMapping
    public ModelAndView showRegistrationForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping()
    public RedirectView registerUserAccount(@ModelAttribute("user") UserRegistrationDto userRegistrationDto, RedirectAttributes redirectAttributes){
        userService.save(userRegistrationDto);
        RedirectView redirectView = new RedirectView("registration",true);
        redirectView.addStaticAttribute("success", "");
        return redirectView;
//        return "redirect:/registration?success";
    }
}
