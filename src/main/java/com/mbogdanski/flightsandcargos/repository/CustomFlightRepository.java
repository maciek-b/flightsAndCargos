package com.mbogdanski.flightsandcargos.repository;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mbogdanski.flightsandcargos.domain.Flight;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomFlightRepository {

  private final FlightRepository flightRepository;

  public List<Flight> findByFlightNumberAndDepartureDate(final Integer number, final LocalDate date) {
    final List<Flight> flights = flightRepository.findByFlightNumber(number);

    return filterByDate(flights, date);
  }

  public List<Flight> findByDepartureAirportAndDepartureDate(final String code, final LocalDate date) {
    final List<Flight> flights = flightRepository.findByDepartureAirport(code);

    return filterByDate(flights, date);
  }

  public List<Flight> findByArrivalAirportAndDepartureDate(final String code, final LocalDate date) {
    final List<Flight> flights = flightRepository.findByArrivalAirport(code);

    return filterByDate(flights, date);
  }

  private List<Flight> filterByDate(final List<Flight> flights, final LocalDate date) {
    return flights.stream().filter(flight -> flight.getDepartureDate().toLocalDate().equals(date)).collect(toList());
  }
}
