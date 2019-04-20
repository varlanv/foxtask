package com.company.foxtask.integration.contollers;

import com.company.foxtask.contollers.BookingController;
import com.company.foxtask.model.entity.Booking;
import com.company.foxtask.model.service.BookingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BookingController controller;
    @MockBean
    private BookingService service;

    @Test
    public void context_should_start() {
        assertNotNull(mockMvc);
        assertNotNull(controller);
        assertNotNull(service);
    }

    @Test
    public void controller_should_return_all() {
        Booking booking1 = new Booking();
        List<Booking> bookings = singletonList(booking1);
        when(service.findAll()).thenReturn(bookings);
        List<Booking> result = controller.findAll();

        verify(service, only()).findAll();
        assertTrue(result.contains(booking1));
    }
}