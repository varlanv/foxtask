package com.company.foxtask.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "foxtask", name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "room_number")
    private Room room;
    @JsonIgnoreProperties("bookings")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Temporal(TemporalType.DATE)
    @Column(name = "booking_date_from")
    private Date dateFrom;
    @Temporal(TemporalType.DATE)
    @Column(name = "booking_date_to")
    private Date dateTo;
    @JsonIgnoreProperties("bookings")
    @JoinTable(
            schema = "foxtask",
            name = "extra_services_bookings",
            joinColumns = @JoinColumn(name = "booking_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "extra_service_id", referencedColumnName = "id"))
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ExtraService> extraServices;
}
