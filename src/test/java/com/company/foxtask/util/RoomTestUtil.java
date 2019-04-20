package com.company.foxtask.util;

import com.company.foxtask.model.entity.Category;
import com.company.foxtask.model.entity.Room;

public class RoomTestUtil {

    public static Room createRoom(int number, String price, Category category) {
        Room room = new Room();
        room.setNumber(number);
        room.setCategory(category);
        room.setAvailable(true);
        room.setPrice(price);
        return room;
    }
}