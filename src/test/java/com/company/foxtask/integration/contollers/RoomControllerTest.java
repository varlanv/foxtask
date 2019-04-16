package com.company.foxtask.integration.contollers;

import com.company.foxtask.contollers.RoomController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomController.class)
@RunWith(SpringRunner.class)
public class RoomControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void controller_should_return_200() throws Exception {
        mockMvc.perform(get("/rooms"))
                .andExpect(status().isOk());
    }
}
