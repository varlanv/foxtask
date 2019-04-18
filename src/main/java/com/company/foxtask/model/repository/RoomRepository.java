package com.company.foxtask.model.repository;

import com.company.foxtask.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> findAllByCategory_NameIgnoreCase(String category);
}