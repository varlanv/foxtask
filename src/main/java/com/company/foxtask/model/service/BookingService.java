package com.company.foxtask.model.service;

import com.company.foxtask.model.entity.Booking;
import com.company.foxtask.model.entity.User;
import com.company.foxtask.model.repository.BookingRepository;
import com.company.foxtask.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void performBooking(User user, Booking booking) {
        userRepository.save(user);
    }
}
