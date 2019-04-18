package com.company.foxtask.util;

import com.company.foxtask.model.entity.dto.BookingDto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class BookingDtoUtil {

    public static BookingDto build(String userEmail, int roomNumber, LocalDateTime from, LocalDateTime to, List<String> services) {
        BookingDto dto = new BookingDto();

        dto.setUserEmail(userEmail);
        dto.setRoomNumber(roomNumber);
        dto.setDateFrom(from);
        dto.setDateTo(to);
        dto.setServices(services);

        return dto;
    }

    public static BookingDto buildPopulated() {
        BookingDto dto = new BookingDto();
        dto.setUserEmail("email");
        dto.setRoomNumber(5);
        dto.setDateFrom(LocalDateTime.now());
        dto.setDateTo(LocalDateTime.now().plusDays(1));
        dto.setServices(Arrays.asList("Cleaning", "Breakfast"));

        return dto;
    }
}