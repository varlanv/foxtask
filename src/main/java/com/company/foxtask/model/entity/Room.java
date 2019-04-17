package com.company.foxtask.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "foxtask")
public class Room {

    @Id
    private Integer number;
    private String category;
    private String price;
    @OneToOne
    private Booking booking;
}
