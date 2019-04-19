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
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class BookingService {

    @Autowired
    private ExtraServiceRepository extraServiceRepository;
    @Autowired
    private RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void performBooking(BookingDto dto) {

        List<ExtraService> extraServices =
                dto.getServices()
                        .stream()
                        .map(extraServiceRepository::findByName)
                        .collect(Collectors.toList());

        Booking booking = new Booking();

        Room room = roomRepository.getOne(dto.getRoomNumber());
        room.setAvailable(false);

        User user = userRepository
                .findByEmail(dto.getUserEmail())
                .orElse(new User(dto.getUserEmail()));


        booking.setRoom(room);
        booking.setUser(user);

        booking.setDateFrom(dto.getDateFrom());
        booking.setDateTo(dto.getDateTo());
        booking.setExtraServices(extraServices);

        bookingRepository.save(booking);
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public String priceForAllBookings(Integer userId) {
        List<Booking> bookings = bookingRepository.findAllByUser_Id(userId);
        BigDecimal decimal = new BigDecimal("0");


        for (Booking booking : bookings) {

            decimal = decimal.add(new BigDecimal(booking.getRoom().getPrice()));

            List<ExtraService> extraServices = booking.getExtraServices();

            for (ExtraService extraService : extraServices) {
                decimal = decimal.add(new BigDecimal(extraService.getPrice()));
            }

            decimal = decimal.multiply(new BigDecimal(DAYS.between(booking.getDateFrom(), booking.getDateTo())));
        }

        return decimal.toPlainString();
    }
}
