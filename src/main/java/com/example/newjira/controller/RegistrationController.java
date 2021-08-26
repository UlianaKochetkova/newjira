package com.example.newjira.controller;

import com.example.newjira.domain.User;
import com.example.newjira.domain.User_Roles;
import com.example.newjira.repos.RoleRepo;
import com.example.newjira.repos.UserRepo;
import com.example.newjira.repos.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;


    @Autowired
    private UserRoleRepo userrolerepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String redirect(Model model){
        return "redirect:/admin";
    }

    @GetMapping("/registration")
    public String registration(String msg, Model model){
        return "registration";
    }

    //POST - не показываем в url
    @PostMapping("/registration")
    public String addUser(User user, Model model){
        User found=userRepo.findUserByUsername(user.getUsername());
        if (found!=null){
            model.addAttribute("message","User exists");
            return "registration";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);

        userRepo.save(user);

        User_Roles ur=new User_Roles();
        ur.setUser(user);
        ur.setRole(roleRepo.findRoleByName("User"));
        userrolerepo.save(ur);

        return "redirect:/login";
    }
}
