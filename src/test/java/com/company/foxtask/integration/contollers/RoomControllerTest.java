package com.company.foxtask.integration.contollers;

import com.company.foxtask.contollers.RoomController;
import com.company.foxtask.model.entity.Category;
import com.company.foxtask.model.entity.Room;
import com.company.foxtask.model.repository.RoomRepository;
import com.company.foxtask.util.RoomTestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomController.class)
@RunWith(SpringRunner.class)
public class RoomControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    RoomRepository repository;

    @Before
    public void mock_repository() {
        Room room = RoomTestUtil.createRoom(5, new Category("LUX", "125"));
        Room room2 = RoomTestUtil.createRoom(3, new Category("STANDARD", "50"));

        when(repository.findAll()).thenReturn(Arrays.asList(room, room2));
    }

    @Test
    public void controller_should_return_200() throws Exception {
        mockMvc.perform(get("/rooms"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void controller_should_return_rooms_list() throws Exception {
        String responseBody = mockMvc.perform(get("/rooms"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Room> list = Arrays.asList(mapper.readValue(responseBody, Room[].class));

        assertThat(list.get(0).getCategory().getName()).isEqualTo("LUX");
        assertThat(list.get(1).getCategory().getName()).isEqualTo("STANDARD");
    }
}