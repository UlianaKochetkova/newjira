package com.example.newjira;

import com.example.newjira.domain.Role;
import com.example.newjira.domain.Status;
import com.example.newjira.domain.User;
import com.example.newjira.domain.User_Roles;
import com.example.newjira.repos.RoleRepo;
import com.example.newjira.repos.StatusRepo;
import com.example.newjira.repos.UserRepo;
import com.example.newjira.repos.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataLoader {
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private StatusRepo statusrepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRoleRepo userrolerepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


//    public com.example.newjira.DataLoader() {
//        loadData();
//    }
    @PostConstruct
    public void loadData(){
        System.out.println("Инициализация БД");
        if (statusrepo.count()==0){
            Status st1=new Status("Not started");
            Status st2=new Status("In progress");
            Status st3=new Status("Completed");
            statusrepo.save(st1);
            statusrepo.save(st2);
            statusrepo.save(st3);
        }
        if (roleRepo.count()==0){
            Role r1=new Role("User");
            Role r2=new Role("Admin");
            roleRepo.save(r1);
            roleRepo.save(r2);
        }
        if (userRepo.count()==0){
            User user=new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setActive(true);

            userRepo.save(user);

            User_Roles ur=new User_Roles();
            ur.setUser(user);
            ur.setRole(roleRepo.findRoleByName("Admin"));
            userrolerepo.save(ur);

            //user
            User user1=new User();
            user1.setUsername("u1");
            user1.setPassword(passwordEncoder.encode("p1"));
            user1.setActive(true);

            userRepo.save(user1);

            User_Roles ur1=new User_Roles();
            ur1.setUser(user1);
            ur1.setRole(roleRepo.findRoleByName("User"));
            userrolerepo.save(ur1);
        }
    }
}
