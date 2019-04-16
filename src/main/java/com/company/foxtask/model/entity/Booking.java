package com.company.foxtask.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class Booking {

    @Id
    private Integer id;
    @OneToOne(mappedBy = "booking")
    private Room room;

    @Transient
    private List<String> services;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
