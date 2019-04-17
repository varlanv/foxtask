package com.company.foxtask.model.entity.dto;

import com.company.foxtask.model.entity.Room;
import com.company.foxtask.model.entity.Service;
import com.company.foxtask.model.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class BookingDto {
    private User user;
    private List<Service> services;
    private Room room;
    private Date date;
}
