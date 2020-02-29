package com.dmytryk.crud.repository;

import com.dmytryk.crud.entry.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class CarDao {
    @Autowired
    private CarRepository repository;
    @Autowired
    private MongoOperations mongoOps;


    public Collection<Car> getCar() {
        return repository.findAll();
    }

    public Car postCar(Car car) {
        return repository.insert(car);
    }

    public String deleteCar(String id) {
        repository.deleteById(id);
        return "Car " + id + " was deleted.";
    }

    public Car updateCar(Car car) {
        Query query = new Query(new Criteria("carId").is(car.getCarId()));
        Update update = new Update();
        update.set("model", car.getModel());
        update.set("year", car.getYear());
        update.set("speed", car.getSpeed());
        return mongoOps.findAndModify(query, update, Car.class);
    }
}
