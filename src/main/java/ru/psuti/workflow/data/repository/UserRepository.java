package ru.psuti.workflow.data.repository;

import ru.psuti.workflow.data.entity.User;

import java.util.List;

public interface UserRepository {

    User findByUsername(String username);

    List<User> findAll();

    void deleteById(String id);

    User findById(String id);

    User save(User user);

}
