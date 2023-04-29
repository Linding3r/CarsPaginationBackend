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
        return cars.stream().map(CarResponse::new).collect(Collectors.toList());
    }

    public Page<CarResponse> getCarsByPage(Pageable pageable) {
        Page<Car> cars = carRepo.findAll(pageable);
        return cars.map(CarResponse::new);
    }

    public Page<CarResponse> getCarsByBrand(String brand, Pageable pageable) {
        Page<Car> cars = carRepo.findByBrand(brand, pageable);
        return cars.map(CarResponse::new);
    }

    public long getTotalCount() {
        return carRepo.count();
    }

    public Page<CarResponse> getCarsByColumnAndValue(String column, String value, Pageable pageable) {
        Page<Car> cars;
        switch (column) {
            case "brand":
                cars = carRepo.findByBrand(value, pageable);
                break;
            case "model":
                cars = carRepo.findByModel(value, pageable);
                break;
            case "color":
                cars = carRepo.findByColor(value, pageable);
                break;
            default:
                throw new IllegalArgumentException("Invalid column type: " + column);
        }
        return cars.map(CarResponse::new);
    }
}