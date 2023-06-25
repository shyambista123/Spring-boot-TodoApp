package com.shyam.todo.controller;

import com.shyam.todo.model.UserDtls;
import com.shyam.todo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(){
        return "auth/register";
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @PostMapping("/create-user")
    public String createUser(@ModelAttribute UserDtls user, HttpSession session){
        if(!userService.checkEmail(user.getEmail())) {
            if (user.getEmail() != null && !user.getEmail().isEmpty() &&
                    user.getFullName() != null && !user.getFullName().isEmpty() &&
                    user.getPassword() != null && !user.getPassword().isEmpty()) {
                userService.createUser(user);
                if(user!=null){
                    session.setAttribute("msg","Account Created Successfully🎉");
                }
                else{

                }
            }
        }
        else{
            session.setAttribute("msg","Something went wrong❌");
            return "redirect:/register";
        }
        return "redirect:/login";
    }
}
