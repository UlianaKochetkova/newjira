package com.example.newjira.repos;

import com.example.newjira.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

//Репозиторий, наследюущий интерфейс
//<T, ID> где T — это сущность для доступа к которой будут использоваться методы, а ID — тип первичного ключа
//Когда мы унаследовались от JpaRepository нам стали доступны стандартные методы над сущностью: сохранение, удаление, получение всех, получение по айди.
// В этих интерфейсах мы можем написать свои методы доступа к сущности: например получить по определенному полю или полям.
// Здесь также есть возможность написать собственный запрос на SQL.
public interface UserRepo extends JpaRepository<User, Integer> {

    User findUserByUsername(String username);
    User findUserById(Integer id);
}
