package com.company.foxtask.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "room", schema = "foxtask")
public class Room {

    @Id
    private Integer number;
    private boolean available;
    @JsonUnwrapped
    @JsonIgnoreProperties("rooms")
    @ManyToOne
    private Category category;

}
