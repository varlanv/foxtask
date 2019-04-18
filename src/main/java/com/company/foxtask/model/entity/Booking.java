package com.company.foxtask.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "bookings"})
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private User user;

    @Column(name = "booking_date_from")
    private LocalDateTime dateFrom;

    @Column(name = "booking_date_to")
    private LocalDateTime dateTo;

    @JsonIgnoreProperties("bookings")
    @JoinTable(
            schema = "foxtask",
            name = "extra_services_bookings",
            joinColumns = @JoinColumn(name = "booking_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "extra_service_id", referencedColumnName = "id"))
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<ExtraService> extraServices;
}