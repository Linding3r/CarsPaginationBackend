package com.example.paginationserverside.api;


import com.example.paginationserverside.dto.CarResponse;
import com.example.paginationserverside.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/all-cars")
    public ResponseEntity<List<CarResponse>> getAllCars() {
        List<CarResponse> carList = carService.getAllCars();
        return ResponseEntity.ok(carList);
    }

    @GetMapping("/sorted")
    public ResponseEntity<Page<CarResponse>> getAllCarsSorted(@PageableDefault(size = 4, sort = {"brand", "kilometers"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<CarResponse> carList = carService.getCarsByPage(pageable);
        return ResponseEntity.ok(carList);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<Page<CarResponse>> getCarsByBrand(@PathVariable String brand, @PageableDefault(size = 3, sort = "brand", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<CarResponse> carList = carService.getCarsByBrand(brand, pageable);
        return ResponseEntity.ok(carList);
    }
}
