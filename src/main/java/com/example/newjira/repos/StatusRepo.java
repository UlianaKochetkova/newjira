package com.example.newjira.repos;

import com.example.newjira.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<Status, Integer> {
    Status findStatusById(Integer id);
    //Status findStatusByStatus_name(String name);
    Status findStatusByName(String name);
}
