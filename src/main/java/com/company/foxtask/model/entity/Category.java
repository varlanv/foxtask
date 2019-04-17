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
    private String price;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Room> rooms;

    public Category(String name, String price) {
        this.name = name;
        this.price = price;
    }
}
