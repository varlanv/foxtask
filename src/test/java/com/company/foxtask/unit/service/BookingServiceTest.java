package com.company.foxtask.unit.service;

import com.company.foxtask.model.entity.Booking;
import com.company.foxtask.model.entity.Category;
import com.company.foxtask.model.entity.ExtraService;
import com.company.foxtask.model.entity.dto.BookingDto;
import com.company.foxtask.model.repository.BookingRepository;
import com.company.foxtask.model.repository.ExtraServiceRepository;
import com.company.foxtask.model.repository.RoomRepository;
import com.company.foxtask.model.repository.UserRepository;
import com.company.foxtask.model.service.BookingService;
import com.company.foxtask.util.BookingDtoUtil;
import com.company.foxtask.util.RoomTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {

    private BookingService service;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private ExtraServiceRepository extraServiceRepository;

    @Before
    public void construct() {
        service = new BookingService(bookingRepository, userRepository, extraServiceRepository, roomRepository);
        when(roomRepository.getOne(5)).thenReturn(RoomTestUtil.createRoom(5, "10", new Category("LUX")));
    }

    @Test
    public void should_call_repositories() {
        BookingDto dto = BookingDtoUtil.buildPopulated();

        service.performBooking(dto);
        verify(roomRepository, only()).getOne(5);
        verify(extraServiceRepository, atLeast(1)).findByName("Cleaning");
        verify(extraServiceRepository, atLeast(1)).findByName("Breakfast");
        verify(userRepository, only()).findByEmail(dto.getUserEmail());
    }

    @Test
    public void should_return_list_by_user_id() {
        List<Booking> bookings = singletonList(new Booking());
        when(bookingRepository.findAllByUser_Id(1)).thenReturn(bookings);
        service.findAllByUserId(1);
        verify(bookingRepository, only()).findAllByUser_Id(1);
        assertEquals(bookings, service.findAllByUserId(1));
    }

    @Test
    public void should_calculate_price() {
        Booking booking = new Booking();
        ExtraService service1 = new ExtraService();
        ExtraService service2 = new ExtraService();
        service1.setPrice("10");
        service2.setPrice("5");

        booking.setRoom(RoomTestUtil.createRoom(1, "100", new Category("LUX")));
        booking.setDateFrom(LocalDate.now());
        booking.setDateTo(LocalDate.now().plusDays(10));
        booking.setExtraServices(asList(service1, service2));

        when(bookingRepository.findAllByUser_Id(1)).thenReturn(asList(booking, booking));

        String totalPrice = service.priceForAllUserBookings(1);

        assertEquals(totalPrice, "2300"); /* (roomprice(100) + service(10) + service(5)) * days(10) * bookings(2))*/
    }

    @Test
    public void should_find_all_bookings_by_user_id() {
        Booking booking = new Booking();
        List<Booking> bookingList = singletonList(booking);
        when(bookingRepository.findAllByUser_Id(1)).thenReturn(bookingList);
        assertEquals(service.findAllByUserId(1), bookingList);
    }
}
