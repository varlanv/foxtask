package com.company.foxtask.unit.contollers;

import com.company.foxtask.contollers.RoomController;
import com.company.foxtask.model.entity.Room;
import com.company.foxtask.model.repository.RoomRepository;
import com.company.foxtask.util.RoomTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoomControllerTest {

    @Mock
    private RoomRepository repository;
    private RoomController controller;

    @Before
    public void controller_should_construct() {
        controller = new RoomController(repository);
    }

    @Test
    public void controller_should_return_all() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Room(), new Room()));
        List<Room> rooms = controller.findAll();

        assertThat(rooms.size()).isEqualTo(2);
        assertThat(rooms).doesNotContainNull();
    }

    @Test
    public void controller_should_find_all_by_category() {
        Room room1 = RoomTestUtil.createRoom(4, "LUX", "125");
        Room room2 = RoomTestUtil.createRoom(2, "DOUBLE", "100");
        Room room3 = RoomTestUtil.createRoom(5, "LUX", "125");

        List<Room> rooms = Arrays.asList(room1, room2, room3);

        when(repository.findAllByCategory("LUX")).thenReturn(rooms.stream()
                .filter(r -> r.getCategory().equals("LUX"))
                .collect(Collectors.toList()));


        assertThat(controller.findByCategory("LUX")).allMatch(room -> room.getCategory().equals("LUX"));
        assertThat(controller.findByCategory("LUX")).noneMatch(room -> room.getCategory().equals("DOUBLE"));
        assertThat(controller.findByCategory("ABC")).isEmpty();

    }
}