package com.mbogdanski.flightsandcargos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mbogdanski.flightsandcargos.domain.Cargo;

@Repository
public interface CargoRepository extends MongoRepository<Cargo, Integer> {
}
