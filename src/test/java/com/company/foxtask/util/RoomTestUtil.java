package com.company.foxtask.util;

import com.company.foxtask.model.entity.Category;
import com.company.foxtask.model.entity.Room;

public class RoomTestUtil {

    public static Room createRoom(int number, Category category) {
        Room room = new Room();
        room.setNumber(number);
        room.setCategory(category);
        return room;
    }
}
