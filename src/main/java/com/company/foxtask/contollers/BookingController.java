package com.company.foxtask.contollers;

import com.company.foxtask.model.entity.Room;
import com.company.foxtask.model.entity.Service;
import com.company.foxtask.model.entity.User;
import com.company.foxtask.model.entity.dto.BookingDto;
import com.company.foxtask.model.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookingController {

    private final BookingService service;

    @Autowired
    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping("/book")
    public void book(@ModelAttribute BookingDto dto) {
//        service.performBooking(dto);

        BookingDto dtoo = new BookingDto();

        User user = new User();
        user.setEmail("EMAIL");

        dtoo.setUser(user);

        Room room = new Room();
        room.setNumber(25);

        dtoo.setRoom(room);

        List<Service> services = new ArrayList<>();

        Service serv = new Service();
        serv.setId(1);
        serv.setId(2);

        dtoo.setServices(services);

        service.performBooking(dtoo);
    }
}
