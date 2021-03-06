package com.company.foxtask.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "foxtask", name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_number")
    private Room room;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "bookings"})
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private User user;

    @Column(name = "booking_date_from")
    private LocalDate dateFrom;

    @Column(name = "booking_date_to")
    private LocalDate dateTo;

    @JsonIgnoreProperties("bookings")
    @JoinTable(
            schema = "foxtask",
            name = "extra_services_bookings",
            joinColumns = @JoinColumn(name = "booking_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "extra_service_id", referencedColumnName = "id"))
    @ManyToMany(fetch = FetchType.EAGER)
    private List<ExtraService> extraServices;
}