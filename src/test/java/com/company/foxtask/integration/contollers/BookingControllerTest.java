package com.company.foxtask.integration.contollers;

import com.company.foxtask.contollers.BookingController;
import com.company.foxtask.model.entity.Booking;
import com.company.foxtask.model.entity.dto.BookingDto;
import com.company.foxtask.model.service.BookingService;
import com.company.foxtask.util.BookingDtoUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BookingController controller;
    @MockBean
    private BookingService service;
    @Autowired
    ObjectMapper mapper;


    @Test
    public void context_should_start() {
        assertNotNull(mockMvc);
        assertNotNull(controller);
        assertNotNull(service);
    }

    @Test
    public void controller_should_post() throws Exception {
        BookingDto bookingDto = BookingDtoUtil.buildPopulated();

        mockMvc.perform(post("/book").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(bookingDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void controller_should_return_all() throws Exception {
        Booking booking1 = new Booking();
        booking1.setId(2);

        when(controller.findAll()).thenReturn(asList(booking1));

        String contentAsString = mockMvc.perform(get("/bookings"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        List<Booking> bookings = asList(mapper.readValue(contentAsString, Booking[].class));

        assertEquals(2, (int) bookings.get(0).getId());
    }

    @Test
    public void controller_should_return_all_bookings_by_user_id() throws Exception {
        Booking booking1 = new Booking();
        booking1.setId(2);

        when(controller.allUserBookings(2)).thenReturn(asList(booking1));

        String contentAsString = mockMvc.perform(get("/user/2/bookings").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        Booking[] bookings = mapper.readValue(contentAsString, Booking[].class);
        assertEquals(booking1.getId(), bookings[0].getId());
    }

    @Test
    public void price_for_all_user_bookings() throws Exception {
        when(service.priceForAllUserBookings(3)).thenReturn("100");

        String contentAsString = mockMvc.perform(get("/user/3/bookings/price").content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertEquals(contentAsString, "100");
    }
}
