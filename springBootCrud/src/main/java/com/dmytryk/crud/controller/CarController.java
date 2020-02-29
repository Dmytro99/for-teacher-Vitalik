package com.dmytryk.crud.controller;

import com.dmytryk.crud.entry.Car;
import com.dmytryk.crud.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping
    public Collection<Car> getCar() {
        return carService.getCars();
    }

    @PostMapping
    public Car postCar(@RequestBody Car car) {
        return carService.postCar(car);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteCar(@PathVariable("id") String id) {
        return carService.deleteCar(id);
    }

    @PutMapping
    public Car updateCar(@RequestBody Car car) {
        return carService.updateCar(car);
    }
}
