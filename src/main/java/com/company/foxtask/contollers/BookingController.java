package com.company.foxtask.contollers;

import com.company.foxtask.model.entity.dto.BookingDto;
import com.company.foxtask.model.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    private final BookingService service;

    @Autowired
    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping("/book")
    public void book(@ModelAttribute BookingDto dto) {
        service.performBooking(dto);
    }
}
