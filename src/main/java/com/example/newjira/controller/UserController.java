package com.example.newjira.controller;

import com.example.newjira.repos.StatusRepo;
import com.example.newjira.repos.TaskRepo;
import com.example.newjira.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    @Autowired
    private UserRepo userrepo;

    @Autowired
    private TaskRepo taskrepo;

    @Autowired
    private StatusRepo statusrepo;

    @GetMapping("/user")
    public String userpage(Model model){
        return "redirect:/showTasks";
    }

    @GetMapping("/showTasks/{id}")
    public String showTasks(@PathVariable("id") Integer id,Model model){
        model.addAttribute("us","user");
        model.addAttribute("tasks",taskrepo.findTasksByUser(userrepo.findUserById(id)));
        return "showTasks";
    }
}
