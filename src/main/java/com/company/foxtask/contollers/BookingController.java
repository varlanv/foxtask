package com.company.foxtask.contollers;

import com.company.foxtask.model.entity.Booking;
import com.company.foxtask.model.entity.Service;
import com.company.foxtask.model.entity.User;
import com.company.foxtask.model.repository.BookingRepository;
import com.company.foxtask.model.repository.ServiceRepository;
import com.company.foxtask.model.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class BookingController {

    private final BookingService sservice;
    @Autowired
    BookingRepository repository;
    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    public BookingController(BookingService service) {
        this.sservice = service;
    }

    @PostMapping("/book")
    public void book(@ModelAttribute User user) {
        Booking booking = new Booking();
        User user1 = new User();
        user1.setEmail("ankaranara");
        user1.setFirstName("ana");
        user1.setLastName("ana");
        List<Booking> bookings = new ArrayList<>();
        Service service = serviceRepository.findById(1).get();
        Service service1 = serviceRepository.findById(2).get();
        List<Service> services = Arrays.asList(service, service1);
        bookings.add(booking);
        booking.setDate(new Date());
        booking.setServices(services);


        services.forEach(s -> s = serviceRepository.findById(s.getId()).get());

        user1.setBookings(bookings);


        sservice.performBooking(user1);
        sservice.performBooking(user1);
        sservice.performBooking(user1);
        sservice.performBooking(user1);
        sservice.performBooking(user1);
        sservice.performBooking(user1);
        sservice.performBooking(user1);
    }
}
