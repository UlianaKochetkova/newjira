package com.example.newjira.repos;

import com.example.newjira.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Role findRoleById(Integer id);
    Role findRoleByName(String name);
}
