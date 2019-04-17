package com.company.foxtask.model.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class BookingDto {

    private String userEmail;
    private Integer roomNumber;
    private Date dateFrom;
    private Date dateTo;
    private List<String> services;
}
