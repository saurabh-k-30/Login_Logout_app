package com.demo.controller;


import com.demo.dto.ChangePasswordDTO;
import com.demo.model.UserDetails;
import com.demo.repository.UserRepository;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @GetMapping("/signin")
    public String login()
    {
        return "login";
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword()
    {
        return "resetPassword";
    }

    @GetMapping("/home")
    public String homepage()
    {
        return "homepage";
    }
    @PostMapping("/createUser")
    public String createUser(@ModelAttribute UserDetails user, HttpSession session) {

        // System.out.println(user);

        boolean f = userService.checkEmail(user.getEmail());

        if (f) {
            session.setAttribute("msg", "Email Id already exists");
        }

        else {
            UserDetails userDetails = userService.createUser(user);
            if (userDetails != null) {
                session.setAttribute("msg", "Registered Successfully");
            } else {
                session.setAttribute("msg", "Something wrong on server");
            }
        }

        return "redirect:/register";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@ModelAttribute ChangePasswordDTO changePasswordDTO, HttpSession session){
        String response = userService.changePassword(changePasswordDTO);
        if(response.equals("Password Changed")){
            session.setAttribute("msg", "Password Changed Successfully");
            return "redirect:/signin";
        } else if(response.equals("Password Do Not Match")){
            session.setAttribute("msg", "Passwords do not match");
        } else if (response.equals("Email Not Registered")){
            session.setAttribute("msg", "Email not registered");
        }
        else{
            session.setAttribute("msg", "Something wrong on server");
        }
        return "";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDetails userDetails, HttpSession session){

        UserDetails userExists = userRepository.findFirstByEmail(userDetails.getEmail());
        if(userExists!=null && userDetails.getPassword().equals(userExists.getPassword())){
            session.setAttribute("msg", "User logged in successfully");
            return "redirect:/home";
        } else{
            session.setAttribute("msg", "User does not exist please register");
            return "redirect:/register";
        }
    }
}
