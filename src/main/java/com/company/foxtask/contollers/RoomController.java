package com.company.foxtask.contollers;

import com.company.foxtask.model.entity.Room;
import com.company.foxtask.model.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {

    private final RoomRepository repository;

    @Autowired
    public RoomController(RoomRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/rooms")
    public List<Room> findAll() {
        return repository.findAll();
    }

    @GetMapping("/rooms/category/{category}")
    public List<Room> findByCategory(@PathVariable String category) {
        return repository.findAllByCategory_NameIgnoreCase(category);
    }
}