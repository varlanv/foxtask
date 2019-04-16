package com.company.foxtask.contollers;

import com.company.foxtask.model.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @PostMapping
    public void book(@RequestBody User user) {

    }
}
