package com.example.paginationserverside.api;


import com.example.paginationserverside.dto.CarResponse;
import com.example.paginationserverside.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@CrossOrigin
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
    public ResponseEntity<Page<CarResponse>> getAllCarsSorted(
            @PageableDefault(size = 4, sort = {"brand", "kilometers"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<CarResponse> carList = carService.getCarsByPage(pageable);
        return ResponseEntity.ok(carList);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<Page<CarResponse>> getCarsByBrand(
            @PathVariable String brand,
            @PageableDefault(size = 3, sort = "brand", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<CarResponse> carList = carService.getCarsByBrand(brand, pageable);
        return ResponseEntity.ok(carList);
    }

    @GetMapping("/total-count")
    public ResponseEntity<Long> getTotalCount() {
        long totalCount = carService.getTotalCount();
        return ResponseEntity.ok(totalCount);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<CarResponse>> getCarsByColumnAndValue(
            @RequestParam String column,
            @RequestParam String value,
            @PageableDefault(size = 12, sort = "brand", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<CarResponse> carList = carService.getCarsByColumnAndValue(column, value, pageable);
        return ResponseEntity.ok(carList);
    }
}
