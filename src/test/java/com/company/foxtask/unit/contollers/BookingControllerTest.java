package com.company.foxtask.unit.contollers;

import com.company.foxtask.contollers.BookingController;
import com.company.foxtask.model.entity.dto.BookingDto;
import com.company.foxtask.model.service.BookingService;
import com.company.foxtask.util.BookingDtoUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BookingControllerTest {

    @Mock
    private BookingService service;
    private BookingController controller;

    @Before
    public void construct() {
        controller = new BookingController(service);
    }

    @Test
    public void controller_should_call_service() {
        BookingDto dto = BookingDtoUtil.buildPopulated();
        controller.book(dto);
        verify(service, only()).performBooking(dto);
    }

    @Test
    public void controller_should_call_service_once() {
        BookingDto bookingDto = BookingDtoUtil.buildPopulated();
        controller.book(bookingDto);
        verify(service, only()).performBooking(bookingDto);
    }
}