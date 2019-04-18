package com.company.foxtask.contollers;

import com.company.foxtask.model.entity.Booking;
import com.company.foxtask.model.entity.dto.BookingDto;
import com.company.foxtask.model.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingController {

    private final BookingService service;

    @Autowired
    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping("/book")
    public void book(@RequestBody BookingDto dto) {
        service.performBooking(dto);
    }

    @GetMapping("/bookings")
    public List<Booking> findAll() {
        return service.findAll();
    }
}