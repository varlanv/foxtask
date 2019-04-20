package com.company.foxtask.model.repository;

import com.company.foxtask.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findAllByUser_Id(Integer id);

    List<Booking> findAllByUser_Email(String email);
}
