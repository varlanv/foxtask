package com.company.foxtask.integration.service;

import com.company.foxtask.exceptions.RoomIsTakenException;
import com.company.foxtask.model.entity.dto.BookingDto;
import com.company.foxtask.model.service.BookingService;
import com.company.foxtask.util.BookingDtoUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@Sql("classpath:/refresh-database-tables.sql")
@RunWith(SpringRunner.class)
public class BookingServiceTest {

    @Autowired
    private BookingService service;

    @Before
    public void context_should_start() {
        assertNotNull(service);
    }

    @Test
    public void should_calculate_price_for_all_bookings() {
        BookingDto dto1 = BookingDtoUtil.build("qwer", 5, LocalDate.now(), LocalDate.now().plusDays(1), asList("Cleaning", "Breakfast"));
        BookingDto dto2 = BookingDtoUtil.build("qwer", 10, LocalDate.now(), LocalDate.now().plusDays(2), emptyList());
        service.performBooking(dto1);

        assertEquals(service.priceForAllUserBookings("qwer"), "90"); // roomprice(75) + service(5) + service(10)

        service.performBooking(dto2);

        assertEquals(service.priceForAllUserBookings("qwer"), "190"); // roomprice(50) * days(2) + previousbooking(90)
    }

    @Test(expected = RoomIsTakenException.class)
    public void if_room_is_taken_should_throw_exception() {
        BookingDto dto = BookingDtoUtil.build("qwerty", 5, LocalDate.now(), LocalDate.now().plusDays(1), asList("Cleaning", "Breakfast"));
        service.performBooking(dto);
        service.performBooking(dto);
    }

    @Test
    public void should_return_all() {
        BookingDto dto1 = BookingDtoUtil.build("qwerty", 5, LocalDate.now(), LocalDate.now().plusDays(1), asList("Cleaning", "Breakfast"));
        BookingDto dto2 = BookingDtoUtil.build("qwerty", 10, LocalDate.now(), LocalDate.now().plusDays(2), emptyList());
        service.performBooking(dto1);
        service.performBooking(dto2);

        assertEquals(service.findAll().size(), 2);
        assertEquals(service.findAll().get(0).getUser().getEmail(), "qwerty");

    }
}
