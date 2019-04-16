package com.company.foxtask.contollers;

import com.company.foxtask.entity.Room;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {

    @GetMapping("/rooms")
    public List<Room> fetchAll() {
        return null;
    }
}
