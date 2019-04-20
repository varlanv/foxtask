package com.company.foxtask;

import com.company.foxtask.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FoxtaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoxtaskApplication.class, args);
    }

    @Autowired
    UserRepository repository;

    @DeleteMapping("delete-all")
    public void deleteAll() {
        repository.deleteall();
    }
}
