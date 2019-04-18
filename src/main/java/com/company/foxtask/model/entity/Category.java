package com.company.foxtask.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "foxtask")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Room> rooms;

    public Category(String name) {
        this.name = name;
    }
}