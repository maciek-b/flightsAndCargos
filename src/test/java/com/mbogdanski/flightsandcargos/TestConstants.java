package com.mbogdanski.flightsandcargos;

import static com.mbogdanski.flightsandcargos.domain.WeightUnit.kg;
import static com.mbogdanski.flightsandcargos.domain.WeightUnit.lb;

import java.time.LocalDate;
import java.util.List;

import com.mbogdanski.flightsandcargos.domain.Cargo;
import com.mbogdanski.flightsandcargos.domain.Flight;
import com.mbogdanski.flightsandcargos.domain.Item;
import com.mbogdanski.flightsandcargos.dto.WeightResponse;

public class TestConstants {


  public static String AIRPORT = "GDA";
  public static LocalDate DATE = LocalDate.of(2015, 11, 12);
  public static final Flight DEPARTING_FLIGHT = Flight.builder()
      .flightId(1)
      .flightNumber(101)
      .departureAirport(AIRPORT)
      .arrivalAirport("XXX")
      .departureDate(DATE)
      .build();

  public static final Flight ARRIVING_FLIGHT_1 = Flight.builder()
      .flightId(2)
      .flightNumber(102)
      .departureAirport("XXX")
      .arrivalAirport(AIRPORT)
      .departureDate(DATE)
      .build();

  public static final Flight ARRIVING_FLIGHT_2 = Flight.builder()
      .flightId(3)
      .flightNumber(103)
      .departureAirport("XXX")
      .arrivalAirport(AIRPORT)
      .departureDate(DATE)
      .build();

  public static final WeightResponse EMPTY_WEIGHT =
      new WeightResponse(0, 0);

  public static final Cargo DEPARTING_CARGO = Cargo.builder()
      .flightId(DEPARTING_FLIGHT.getFlightId())
      .cargoList(List.of(
          new Item(0, 2, kg, 1),
          new Item(0, 3, kg, 1),
          new Item(0, 4, lb, 1)
      ))
      .baggageList(List.of(
          new Item(0, 5, kg, 7),
          new Item(0, 6, kg, 8)
      ))
      .build();

  public static final Cargo ARRIVING_CARGO_1 = Cargo.builder()
      .flightId(ARRIVING_FLIGHT_1.getFlightId())
      .cargoList(List.of(
          new Item(0, 2, kg, 1),
          new Item(0, 3, kg, 1)
      ))
      .baggageList(List.of(
          new Item(0, 5, kg, 7)
      ))
      .build();

  public static final Cargo ARRIVING_CARGO_2 = Cargo.builder()
      .flightId(ARRIVING_FLIGHT_2.getFlightId())
      .cargoList(List.of(
          new Item(0, 4, lb, 1)
      ))
      .baggageList(List.of(
          new Item(0, 5, kg, 7),
          new Item(0, 6, kg, 8)
      ))
      .build();
}
