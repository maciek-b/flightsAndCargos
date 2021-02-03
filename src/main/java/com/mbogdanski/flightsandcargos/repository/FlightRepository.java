package com.mbogdanski.flightsandcargos.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mbogdanski.flightsandcargos.domain.Flight;

@Repository
public interface FlightRepository extends MongoRepository<Flight, Integer> {
  Optional<Flight> findByFlightNumberAndDepartureDate(final Integer number, final LocalDate date);

  List<Flight> findByDepartureAirportAndDepartureDate(final String code, final LocalDate date);

  List<Flight> findByArrivalAirportAndDepartureDate(final String code, final LocalDate date);
}
