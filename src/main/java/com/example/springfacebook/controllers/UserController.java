package com.example.springfacebook.controllers;

import com.example.springfacebook.entities.User;
import com.example.springfacebook.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    //Constructor injection of the UserService interface
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }





//gets the client request and displays the login page
    @GetMapping("/")
    public String displayLogin(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }

    @GetMapping("/home")
    public String takeToHome(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }

    //the login form is submitted to this route and the information is verified against the database
    @PostMapping("/login")
    public String login(@ModelAttribute("user")User user, HttpServletRequest request, RedirectAttributes redirectAttributes){

        //For Validation of new user
        User validUser = userService.getUser(user.getUserName(),user.getPassWord());

        if (validUser == null) {
            redirectAttributes.addFlashAttribute("not_found", "Username or password is not valid, please check again or sign up");
            return "login";
        }else{
            request.getSession().invalidate();
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(1000);
            session.setAttribute("user_session",validUser);
            return "comment"/*"redirect:/newsfeed"*/;
        }
    }

        //for signup: gets client request and loads the signup page

    @GetMapping("/signup")
    public String displaySignup(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    //the signup form is submitted to this route and the details are registered to save the new user in the database
    @PostMapping("/signupPost")
    public String register(@ModelAttribute("user")User user){
        userService.registerUser(user);
        return "login";
    }


        //FOR LOG OUT: gets the client request to log out and redirects to the default login page

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}