package com.company.foxtask.model.service;

import com.company.foxtask.model.entity.Booking;
import com.company.foxtask.model.entity.dto.BookingDto;
import com.company.foxtask.model.repository.BookingRepository;
import com.company.foxtask.model.repository.RoomRepository;
import com.company.foxtask.model.repository.ServiceRepository;
import com.company.foxtask.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void performBooking(BookingDto dto) {
        userRepository.save(dto.getUser());
        Booking booking = new Booking();
        booking.setDateFrom(new Date());
        dto.getServices().forEach(s -> s = serviceRepository.getOne(s.getId()));
        booking.setServices(dto.getServices());

        booking.setUser(dto.getUser());
//        booking.setRoom(roomRepository.getOne(dto.getRoom().getNumber()));

        bookingRepository.save(booking);
    }
}
