package com.example.newjira.repos;

import com.example.newjira.domain.Task;
import com.example.newjira.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Integer> {
    void deleteById(Integer id);
    Task findTaskById(Integer id);
    List<Task> findTasksByUser(User user);
}
