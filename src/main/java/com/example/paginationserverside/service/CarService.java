package com.example.paginationserverside.service;

import com.example.paginationserverside.dto.CarResponse;
import com.example.paginationserverside.entity.Car;
import com.example.paginationserverside.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepo carRepo;


    public List<CarResponse> getAllCars() {
        List<Car> cars = carRepo.findAll();
        return cars.stream().map(car -> {
            CarResponse carResponse = new CarResponse();
            carResponse.setId(car.getId());
            carResponse.setBrand(car.getBrand());
            carResponse.setModel(car.getModel());
            carResponse.setColor(car.getColor());
            carResponse.setKilometers(car.getKilometers());
            carResponse.setCreated(car.getCreated());
            carResponse.setUpdated(car.getUpdated());
            return carResponse;
        }).collect(Collectors.toList());
    }

    public Page<CarResponse> getCarsByPage(Pageable pageable) {
        Page<Car> cars = carRepo.findAll(pageable);
        return cars.map(car -> {
            CarResponse carResponse = new CarResponse();
            carResponse.setId(car.getId());
            carResponse.setBrand(car.getBrand());
            carResponse.setModel(car.getModel());
            carResponse.setColor(car.getColor());
            carResponse.setKilometers(car.getKilometers());
            carResponse.setCreated(car.getCreated());
            carResponse.setUpdated(car.getUpdated());
            return carResponse;
        });
    }

    public Page<CarResponse> getCarsByBrand(String brand, Pageable pageable) {
        Page<Car> cars = carRepo.findByBrand(brand, pageable);
        return cars.map(car -> {
            CarResponse carList = new CarResponse();
            carList.setId(car.getId());
            carList.setBrand(car.getBrand());
            carList.setModel(car.getModel());
            carList.setColor(car.getColor());
            carList.setKilometers(car.getKilometers());
            return carList;
        });
    }
}