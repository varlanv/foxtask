package com.company.foxtask.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "foxtask", name = "extra_services")
public class ExtraService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonIgnoreProperties("extraServices")
    @ManyToMany(mappedBy = "extraServices", fetch = FetchType.LAZY)
    private List<Booking> bookings;
    private String name;
    private String price;

    public ExtraService(String name) {
        this.name = name;
    }
}
