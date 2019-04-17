package com.company.foxtask.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "foxtask", name = "extra_services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany
    private List<Booking> bookings;
    private String service;
    private String price;

    public Service(String service) {
        this.service = service;
    }
}
