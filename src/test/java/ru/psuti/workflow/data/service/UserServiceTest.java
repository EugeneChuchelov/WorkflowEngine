package ru.psuti.workflow.data.service;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.psuti.workflow.WorkflowApplication;
import ru.psuti.workflow.data.entity.User;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WorkflowApplication.class)
//@WebAppConfiguration
class UserServiceTest {
    @Autowired
    private UserService userService;

    private static List<User> users;

    @BeforeAll
    static void init(){
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("pass1");


        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("pass2");


        User user3 = new User();
        user3.setUsername("user3");
        user3.setPassword("pass3");

        users = new LinkedList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    @BeforeEach
    public void setUp(){
        userService.save(users.get(0));
        userService.save(users.get(1));
        userService.save(users.get(2));
    }

    @AfterEach
    public void tearDown(){
        for(User user : userService.findAll())
            userService.delete(user.getId());
    }

    @Test
    public void save() {
        userService.save(users.get(0));
        userService.save(users.get(1));
        userService.save(users.get(2));
    }

    @Test
    public void findAll() {
        Assert.assertTrue(!userService.findAll().isEmpty());
    }

    @Test
    public void delete() {
        String id = userService.findOne("user3").getId();
        userService.delete(id);
    }

    @Test
    public void findOne() {
        Assert.assertNotNull(userService.findOne("user2"));
    }

    @Test
    public void findById() {
        String id = userService.findOne("user2").getId();
        Assert.assertNotNull(userService.findById(id));
    }

    @Test
    public void update() {
        User user = userService.findOne("user1");
        user.setUsername("user4");
        userService.update(user);
        Assert.assertNotNull(userService.findOne("user4"));
    }
}