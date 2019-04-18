package com.company.foxtask.contollers;

import com.company.foxtask.model.entity.Booking;
import com.company.foxtask.model.entity.User;
import com.company.foxtask.model.repository.BookingRepository;
import com.company.foxtask.model.repository.UserRepository;
import com.company.foxtask.model.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingService bookingService;
    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody User user) {
        repository.save(user);
    }

    @GetMapping("/user/{id}/bookings")
    public List<Booking> allBookings(@PathVariable Integer id) {
        return bookingRepository.findAllByUser_Id(id);
    }

    @GetMapping("/user/{id}/bookings/price")
    public String totalPrice(@PathVariable Integer id) {
        return bookingService.priceForAllBookings(id);
    }
}