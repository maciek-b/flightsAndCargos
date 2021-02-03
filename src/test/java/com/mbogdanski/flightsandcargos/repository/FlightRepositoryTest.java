package com.mbogdanski.flightsandcargos.repository;

import static com.mbogdanski.flightsandcargos.TestConstants.AIRPORT;
import static com.mbogdanski.flightsandcargos.TestConstants.ARRIVING_FLIGHT_1;
import static com.mbogdanski.flightsandcargos.TestConstants.ARRIVING_FLIGHT_2;
import static com.mbogdanski.flightsandcargos.TestConstants.DATE;
import static com.mbogdanski.flightsandcargos.TestConstants.DEPARTING_FLIGHT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.mbogdanski.flightsandcargos.domain.Flight;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class FlightRepositoryTest {

  @Autowired
  private FlightRepository flightRepository;

  @Before
  public void setUp() {
    flightRepository.save(DEPARTING_FLIGHT);
    flightRepository.save(ARRIVING_FLIGHT_1);
    flightRepository.save(ARRIVING_FLIGHT_2);
  }

  @Test
  public void should_find_flight_by_flight_number_and_departure_date() {
    final Optional<Flight> flight = flightRepository.findByFlightNumberAndDepartureDate(DEPARTING_FLIGHT.getFlightNumber(), DATE);

    assertTrue(flight.isPresent());
    assertEquals(DEPARTING_FLIGHT, flight.get());
  }

  @Test
  public void should_find_departing_flights_by_airport_and_departure_date() {
    final List<Flight> flights = flightRepository.findByDepartureAirportAndDepartureDate(AIRPORT, DATE);

    assertEquals(1, flights.size());
    assertEquals(DEPARTING_FLIGHT, flights.get(0));
  }

  @Test
  public void should_find_arriving_flights_by_airport_and_departure_date() {
    final List<Flight> flights = flightRepository.findByArrivalAirportAndDepartureDate(AIRPORT, DATE);

    assertEquals(2, flights.size());
    assertEquals(ARRIVING_FLIGHT_1, flights.get(0));
    assertEquals(ARRIVING_FLIGHT_2, flights.get(1));
  }

  @After
  public void cleanUp() {
    flightRepository.deleteAll();
  }
}
