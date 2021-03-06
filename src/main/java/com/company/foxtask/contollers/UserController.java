package com.company.foxtask.contollers;

import com.company.foxtask.model.entity.User;
import com.company.foxtask.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody User user) {
        repository.save(user);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Integer id) {
        return repository.getOne(id);
    }
}