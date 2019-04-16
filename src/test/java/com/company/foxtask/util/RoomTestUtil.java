package com.company.foxtask.util;

import com.company.foxtask.model.entity.Room;

public class RoomTestUtil {

    public static Room createRoom(int number, String category, String price) {
        Room room = new Room();
        room.setNumber(number);
        room.setCategory(category);
        room.setPrice(price);
        return room;
    }
}
