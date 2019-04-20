package com.company.foxtask.contollers;

import com.company.foxtask.model.entity.Booking;
import com.company.foxtask.model.entity.dto.BookingDto;
import com.company.foxtask.model.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookingController {

    private final BookingService service;

    @Autowired
    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping("/book")
    public void book(@RequestBody @Valid BookingDto dto) {
        service.performBooking(dto);
    }

    @GetMapping("/bookings")
    public List<Booking> findAll() {
        return service.findAll();
    }

    @GetMapping("/user/{email}/bookings")
    public List<Booking> allBookingsByEmail(@PathVariable String email) {
        return service.findAllByUserEmail(email);
    }

    @GetMapping("/user/{email}/bookings/price")
    public String totalPriceByEmail(@PathVariable String email) {
        return service.priceForAllUserBookings(email);
    }
}