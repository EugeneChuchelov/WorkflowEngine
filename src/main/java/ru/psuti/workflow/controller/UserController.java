package ru.psuti.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.psuti.workflow.data.entity.Role;
import ru.psuti.workflow.data.entity.User;
import ru.psuti.workflow.data.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/signup")
    public User saveUser(@RequestBody User user) {
        Set<Role> defaultRole = new HashSet<>(1);
        defaultRole.add(Role.READ);
        user.setRoles(defaultRole);
        return userService.save(user);
    }

    @PreAuthorize(RoleConstants.ADMIN)
    @GetMapping
    public List<User> listUser() {
        return userService.findAll();
    }

    @PreAuthorize(RoleConstants.ADMIN)
    @GetMapping("/{id}")
    public User getOne(@PathVariable String id) {
        return userService.findById(id);
    }

    @PreAuthorize(RoleConstants.ADMIN)
    @PutMapping
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @PreAuthorize(RoleConstants.ADMIN)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }
}
