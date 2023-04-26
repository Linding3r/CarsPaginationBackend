package com.example.paginationserverside.repository;


import com.example.paginationserverside.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {
    @Query("SELECT c FROM Car c WHERE c.brand = :brand")
    Page<Car> findByBrand(String brand, Pageable pageable);

    long count();

    Page<Car> findByModel(String val, Pageable pageable);

    Page<Car> findByColor(String val, Pageable pageable);
}
