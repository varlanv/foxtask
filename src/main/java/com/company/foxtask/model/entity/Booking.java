package com.company.foxtask.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "foxtask")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name="room_number")
    private Room room;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Temporal(TemporalType.DATE)
    @Column(name = "booking_date_from")
    private Date dateFrom;
    @Temporal(TemporalType.DATE)
    @Column(name = "booking_date_to")
    private Date dateTo;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "bookings_extra_services",
            schema = "foxtask",
            joinColumns = @JoinColumn(name = "booking_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "extra_service_id", referencedColumnName = "id"))
    private List<Service> services;
}
