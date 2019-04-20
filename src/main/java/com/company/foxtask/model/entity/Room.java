package com.company.foxtask.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "room", schema = "foxtask")
public class Room {

    @Id
    private Integer number;
    private boolean available;
    private String price;

    @JsonUnwrapped
    @JsonIgnoreProperties("rooms")
    @ManyToOne
    private Category category;


}