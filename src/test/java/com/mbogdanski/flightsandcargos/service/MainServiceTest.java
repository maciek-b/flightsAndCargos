package com.mbogdanski.flightsandcargos.service;

import static com.mbogdanski.flightsandcargos.TestConstants.AIRPORT;
import static com.mbogdanski.flightsandcargos.TestConstants.ARRIVING_CARGO_1;
import static com.mbogdanski.flightsandcargos.TestConstants.ARRIVING_CARGO_2;
import static com.mbogdanski.flightsandcargos.TestConstants.ARRIVING_FLIGHT_1;
import static com.mbogdanski.flightsandcargos.TestConstants.ARRIVING_FLIGHT_2;
import static com.mbogdanski.flightsandcargos.TestConstants.DATE;
import static com.mbogdanski.flightsandcargos.TestConstants.DEPARTING_CARGO;
import static com.mbogdanski.flightsandcargos.TestConstants.DEPARTING_FLIGHT;
import static com.mbogdanski.flightsandcargos.TestConstants.EMPTY_WEIGHT;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mbogdanski.flightsandcargos.dto.FlightResponse;
import com.mbogdanski.flightsandcargos.dto.WeightResponse;
import com.mbogdanski.flightsandcargos.repository.CargoRepository;
import com.mbogdanski.flightsandcargos.repository.FlightRepository;

@RunWith(MockitoJUnitRunner.class)
public class MainServiceTest {

  @Mock
  private FlightRepository flightRepository;
  @Mock
  private CargoRepository cargoRepository;
  @InjectMocks
  private MainService mainService;

  @Test
  public void should_return_empty_weight_response_when_flight_does_not_exist() {
    when(flightRepository.findByFlightNumberAndDepartureDate(DEPARTING_FLIGHT.getFlightNumber(), DEPARTING_FLIGHT.getDepartureDate()))
        .thenReturn(Optional.empty());

    final WeightResponse weightResponse = mainService.calcWeight(DEPARTING_FLIGHT.getFlightNumber(), DEPARTING_FLIGHT.getDepartureDate());

    assertEquals(EMPTY_WEIGHT, weightResponse);
  }

  @Test
  public void should_return_empty_weight_response_when_cargo_does_not_exist() {
    when(flightRepository.findByFlightNumberAndDepartureDate(DEPARTING_FLIGHT.getFlightNumber(), DEPARTING_FLIGHT.getDepartureDate()))
        .thenReturn(Optional.of(DEPARTING_FLIGHT));
    when(cargoRepository.findById(DEPARTING_FLIGHT.getFlightId()))
        .thenReturn(Optional.empty());

    final WeightResponse weightResponse = mainService.calcWeight(DEPARTING_FLIGHT.getFlightNumber(), DEPARTING_FLIGHT.getDepartureDate());

    assertEquals(EMPTY_WEIGHT, weightResponse);
  }

  @Test
  public void should_return_calculated_weight_response() {
    when(flightRepository.findByFlightNumberAndDepartureDate(DEPARTING_FLIGHT.getFlightNumber(), DEPARTING_FLIGHT.getDepartureDate()))
        .thenReturn(Optional.of(DEPARTING_FLIGHT));
    when(cargoRepository.findById(DEPARTING_FLIGHT.getFlightId()))
        .thenReturn(Optional.of(DEPARTING_CARGO));

    final WeightResponse weightResponse = mainService.calcWeight(DEPARTING_FLIGHT.getFlightNumber(), DEPARTING_FLIGHT.getDepartureDate());

    assertEquals(new WeightResponse(2 + 3 + (4 * 0.4535924), 11), weightResponse);
  }

  @Test
  public void should_return_calculated_flight_response() {
    when(flightRepository.findByDepartureAirportAndDepartureDate(AIRPORT, DATE))
        .thenReturn(List.of(DEPARTING_FLIGHT));
    when(flightRepository.findByArrivalAirportAndDepartureDate(AIRPORT, DATE))
        .thenReturn(List.of(ARRIVING_FLIGHT_1, ARRIVING_FLIGHT_2));
    when(cargoRepository.findAllById(List.of(DEPARTING_FLIGHT.getFlightId())))
        .thenReturn(List.of(DEPARTING_CARGO));
    when(cargoRepository.findAllById(List.of(ARRIVING_FLIGHT_1.getFlightId(), ARRIVING_FLIGHT_2.getFlightId())))
        .thenReturn(List.of(ARRIVING_CARGO_2, ARRIVING_CARGO_1));

    final FlightResponse flightResponse = mainService.getFlightsInfo(AIRPORT, DATE);

    assertEquals(new FlightResponse(1, 2, 7 + 8, 7 + 7 + 8), flightResponse);
  }
}
