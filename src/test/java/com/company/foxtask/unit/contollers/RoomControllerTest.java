package com.company.foxtask.unit.contollers;

import com.company.foxtask.contollers.RoomController;
import com.company.foxtask.model.entity.Category;
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

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
        Category category1 = new Category("LUX");
        Category category2 = new Category("DOUBLE");
        Room room1 = RoomTestUtil.createRoom(4, "", category1);
        Room room2 = RoomTestUtil.createRoom(2, "", category2);

        List<Room> rooms = Arrays.asList(room1, room2);

        when(repository.findAllByCategory_NameIgnoreCase("LUX")).thenReturn(rooms.stream()
                .filter(r -> r.getCategory().getName().equals("LUX"))
                .collect(Collectors.toList()));
        when(repository.findAllByCategory_NameIgnoreCase("DOUBLE")).thenReturn(rooms.stream()
                .filter(r -> r.getCategory().getName().equals("DOUBLE"))
                .collect(Collectors.toList()));

        assertEquals(controller.findByCategory("LUX"), singletonList(room1));
        assertEquals(controller.findByCategory("DOUBLE"), singletonList(room2));
        assertEquals(controller.findByCategory("LUX").size(), 1);
        assertEquals(controller.findByCategory("DOUBLE").size(), 1);

    }
}