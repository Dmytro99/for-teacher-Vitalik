package com.dmytryk.crud.service;

import com.dmytryk.crud.entry.Car;
import com.dmytryk.crud.repository.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CarService {
    @Autowired
    private CarDao carDao;

    public Collection<Car> getCars() {
        return carDao.getCar();
    }

    public Car postCar(Car car) {
        return carDao.postCar(car);
    }

    public String deleteCar(String id) {
        return carDao.deleteCar(id);
    }

    public Car updateCar(Car car) {
        return carDao.updateCar(car);
    }
}
