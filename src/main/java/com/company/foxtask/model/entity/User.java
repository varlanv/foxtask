package com.company.foxtask.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "usr", schema = "foxtask")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    @JsonIgnoreProperties("user")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.REMOVE})
    private List<Booking> bookings;
}