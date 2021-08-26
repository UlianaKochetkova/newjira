package com.example.newjira.controller;

import com.example.newjira.domain.Task;
import com.example.newjira.domain.User;
import com.example.newjira.domain.User_Roles;
import com.example.newjira.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class AdminController {
    @Autowired
    private UserRepo userrepo;

    @Autowired
    private TaskRepo taskrepo;

    @Autowired
    private StatusRepo statusrepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRoleRepo userrolerepo;


    @GetMapping("/admin")
    public String admin(Model model){
        return "admin";
    }

    @GetMapping("/chRole/{id}")
    public String getChRole(@PathVariable("id") Integer id, Model model){
        User user=userrepo.findUserById(id);
        model.addAttribute("curuser",user);
        //добавим текущую роль?
        model.addAttribute("currole",userrolerepo.findUser_RolesByUser(user));
        //Добавим список ролей
        model.addAttribute("roles",roleRepo.findAll());
        return "chRole";
    }

    @PostMapping("/chRole")
    public String changeRole(@RequestParam("roles") String roles, User user, Model model) throws ParseException {
        User_Roles old=userrolerepo.findUser_RolesByUser(user);
        userrolerepo.delete(old);

        User_Roles ur=new User_Roles();
        ur.setUser(user);
        ur.setRole(roleRepo.findRoleByName(roles));
        userrolerepo.save(ur);

        return "redirect:/showAccounts";
    }
    ////////////////////////////////////////////////////////////////////
    @GetMapping("/showAccounts")
    public String showAccounts(Model model){
        model.addAttribute("users",userrepo.findAll());
        model.addAttribute("userrolerepo",userrolerepo);
        return "showAccounts";
    }

    @GetMapping("/showTasks")
    public String showTasks(Model model){
        model.addAttribute("us","admin");
        model.addAttribute("tasks",taskrepo.findAll());
        return "showTasks";
    }

    ////////////////////////////////////////////////////////////////////

    @GetMapping ("/addTask")
    public String getAddTask(Model model){
        //Извлечь список всех юзеров
        List<User> users=userrepo.findAll();
        if (users.size()==0){
            model.addAttribute("rand",0);
        }
        else {
            //Извлечь список идентификаторов
            Random random_method = new Random();
            ArrayList<Integer> ids = new ArrayList<Integer>();
            for (int i = 0; i < users.size(); i++) {
                ids.add(users.get(i).getId());
            }
            //Взять рандомный
            model.addAttribute("rand", ids.get(random_method.nextInt(ids.size())));
            model.addAttribute("users",users);
            //Назначить дату текущую
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            model.addAttribute("curdate",format.format(new Date()));

        }
        return "addTask";
    }

    @PostMapping("/addTask")
    public String addTask(@RequestParam("user_id") Integer userid,@RequestParam("blackdate1") String date1,@RequestParam("blackdate2") String date2,Task t, Model model) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        t.setDate1(format.parse(date1));
        t.setDate2(format.parse(date2));
        t.setUser(userrepo.findUserById(userid));
        t.setStatus(statusrepo.findStatusByName("Not started"));
        taskrepo.save(t);
        return getAddTask(model);
    }


    ///////////////////////////////////////////////////////////////////////////

    @GetMapping("delete/{id}")
    public String deleteTask(@PathVariable("id") Integer id){
        taskrepo.deleteById(id);
        return "redirect:/showTasks";
    }

    @GetMapping("/modify/{id}")
    public String modifyFrom(@PathVariable("id") Integer id, Model model){
        Task task = taskrepo.findTaskById(id);
        model.addAttribute("task", task);
        model.addAttribute("blackdate1",task.getDate1String());
        model.addAttribute("blackdate2",task.getDate2String());
        return "modify";
    }

    @PostMapping("/modify")
    public String updateUser(@RequestParam("blackdate1") String date1,@RequestParam("blackdate2") String date2,@RequestParam("user_id") String user_id, @RequestParam("st") String st, Task task, Model model) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        task.setDate1(format.parse(date1));
        task.setDate2(format.parse(date2));

        task.setUser(userrepo.findUserById(Integer.parseInt(user_id)));
        task.setStatus(statusrepo.findStatusByName(st));

        taskrepo.save(task);
        return "redirect:/showTasks";
    }

    @GetMapping("/showTask/{id}")
    public String showTask(@PathVariable("id") Integer id, Model model){
        Task task = taskrepo.findTaskById(id);
        model.addAttribute("task", task);
        model.addAttribute("blackdate1",task.getDate1String());
        model.addAttribute("blackdate2",task.getDate2String());
        return "showTask";
    }

    @GetMapping("/showTask")
    public String showTask1(){
        return "redirect:/showTasks";
    }
}

