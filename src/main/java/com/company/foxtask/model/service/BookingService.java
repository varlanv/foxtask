package com.company.foxtask.model.service;

import com.company.foxtask.exceptions.RoomIsTakenException;
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

    private final ExtraServiceRepository extraServiceRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, ExtraServiceRepository extraServiceRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.extraServiceRepository = extraServiceRepository;
        this.roomRepository = roomRepository;
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public List<Booking> findAllByUserId(Integer id) {
        return bookingRepository.findAllByUser_Id(id);
    }

    @Transactional
    public void performBooking(BookingDto dto) {
        Booking booking = new Booking();

        Room room = roomRepository.getOne(dto.getRoomNumber());
        if (!room.isAvailable()) throw new RoomIsTakenException("Room is already taken");
        room.setAvailable(false);

        User user = userRepository
                .findByEmail(dto.getUserEmail())
                .orElse(new User(dto.getUserEmail()));

        List<ExtraService> extraServices =
                dto.getServices()
                        .stream()
                        .map(extraServiceRepository::findByName)
                        .collect(Collectors.toList());

        booking.setRoom(room);
        booking.setUser(user);
        booking.setDateFrom(dto.getDateFrom());
        booking.setDateTo(dto.getDateTo());
        booking.setExtraServices(extraServices);

        bookingRepository.save(booking);
    }

    public String priceForAllUserBookings(Integer userId) {
        List<Booking> bookings = bookingRepository.findAllByUser_Id(userId);
        BigDecimal result = new BigDecimal("0");

        for (Booking booking : bookings) {
            BigDecimal temp = new BigDecimal("0");
            temp = temp.add(new BigDecimal(booking.getRoom().getPrice()));
            List<ExtraService> extraServices = booking.getExtraServices();

            for (ExtraService extraService : extraServices) {
                temp = temp.add(new BigDecimal(extraService.getPrice()));
            }
            temp = temp.multiply(new BigDecimal(DAYS.between(booking.getDateFrom(), booking.getDateTo())));
            result = result.add(temp);
        }

        return result.toPlainString();
    }
}