package com.dmytryk.crud.repository;

import com.dmytryk.crud.entry.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car, String> {
}
