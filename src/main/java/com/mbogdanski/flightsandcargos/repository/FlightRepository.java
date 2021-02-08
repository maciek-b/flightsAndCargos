package com.mbogdanski.flightsandcargos.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mbogdanski.flightsandcargos.domain.Flight;

@Repository
public interface FlightRepository extends MongoRepository<Flight, Integer> {

  List<Flight> findByFlightNumber(final Integer number);

  List<Flight> findByDepartureAirport(final String code);

  List<Flight> findByArrivalAirport(final String code);
}
