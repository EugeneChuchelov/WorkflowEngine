package ru.psuti.workflow.data.service;

import ru.psuti.workflow.data.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> findAll();

    void delete(String id);

    User findOne(String username);

    User findById(String id);

    User update(User user);

}
