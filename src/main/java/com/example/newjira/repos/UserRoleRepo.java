package com.example.newjira.repos;

import com.example.newjira.domain.User;
import com.example.newjira.domain.User_Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepo extends JpaRepository<User_Roles, Integer> {
    User_Roles findUser_RolesByUser(User user);
    User_Roles findUser_RolesByUser_Id(Integer id);
}
