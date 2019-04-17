package com.company.foxtask.model.service;

import com.company.foxtask.model.entity.Booking;
import com.company.foxtask.model.entity.ExtraService;
import com.company.foxtask.model.entity.Room;
import com.company.foxtask.model.entity.User;
import com.company.foxtask.model.entity.dto.BookingDto;
import com.company.foxtask.model.repository.BookingRepository;
import com.company.foxtask.model.repository.ExtraServiceRepository;
import com.company.foxtask.model.repository.RoomRepository;
import com.company.foxtask.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    @Autowired
    private ExtraServiceRepository extraServiceRepository;
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void performBooking(BookingDto dto) {
        dto.setUserEmail("email");
        dto.setRoomNumber(5);
        dto.setDateFrom(new Date());
        dto.setDateTo(new Date());
        dto.setServices(new ArrayList<String>(Arrays.asList("Cleaning", "Breakfast")));


        List<ExtraService> extraServices = new ArrayList<>();


        dto.getServices().forEach(s -> extraServices.add(extraServiceRepository.findByName(s)));


        Booking booking = new Booking();

        Room room = roomRepository.getOne(dto.getRoomNumber());
        room.setAvailable(false);

        User user = new User();
        user.setEmail(dto.getUserEmail());

        booking.setRoom(room);
        booking.setUser(user);

        booking.setDateFrom(dto.getDateFrom());
        booking.setDateTo(dto.getDateTo());
        booking.setExtraServices(extraServices);


        bookingRepository.save(booking);
    }
}
