package com.example.paginationserverside.dto;

import com.example.paginationserverside.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CarResponse {

    int id;
    String brand;
    String model;
    String color;
    int kilometers;
    LocalDateTime created;
    LocalDateTime updated;

    public CarResponse(Car c) {
        this.id = c.getId();
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.color = c.getColor();
        this.kilometers = c.getKilometers();
        this.created = c.getCreated();
        this.updated = c.getUpdated();
    }
}
