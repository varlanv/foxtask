package com.company.foxtask.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Room {

    @Id
    private Integer number;
    private String category;
    private String price;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(number, room.number) &&
                Objects.equals(category, room.category) &&
                Objects.equals(price, room.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number, category, price);
    }
}
