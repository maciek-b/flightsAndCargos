package com.mbogdanski.flightsandcargos.service;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mbogdanski.flightsandcargos.domain.Cargo;
import com.mbogdanski.flightsandcargos.domain.Flight;
import com.mbogdanski.flightsandcargos.domain.Item;
import com.mbogdanski.flightsandcargos.domain.WeightUnit;
import com.mbogdanski.flightsandcargos.dto.FlightResponse;
import com.mbogdanski.flightsandcargos.dto.WeightResponse;
import com.mbogdanski.flightsandcargos.repository.CargoRepository;
import com.mbogdanski.flightsandcargos.repository.CustomFlightRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MainService {

  private final CustomFlightRepository flightRepository;
  private final CargoRepository cargoRepository;

  public WeightResponse calcWeight(final Integer flightNumber, final LocalDate date) {
    final List<Flight> flight = flightRepository.findByFlightNumberAndDepartureDate(flightNumber, date);
    if (flight.isEmpty()) {
      return new WeightResponse(0, 0);
    }

    final Optional<Cargo> cargo = cargoRepository.findById(flight.get(0).getFlightId());
    if (cargo.isEmpty()) {
      return new WeightResponse(0, 0);
    }

    return new WeightResponse(
        calcTotalWeightInKg(cargo.get().getCargoList()),
        calcTotalWeightInKg(cargo.get().getBaggageList())
    );
  }

  public FlightResponse getFlightsInfo(final String airportCode, final LocalDate date) {
    final List<Integer> departures = flightRepository.findByDepartureAirportAndDepartureDate(airportCode, date)
        .stream()
        .map(Flight::getFlightId)
        .collect(toList());
    final List<Integer> arrivals = flightRepository.findByArrivalAirportAndDepartureDate(airportCode, date)
        .stream()
        .map(Flight::getFlightId)
        .collect(toList());

    int departureCargoCount = 0;
    int arrivalCargoCount = 0;
    final Iterable<Cargo> departureCargos = cargoRepository.findAllById(departures);
    final Iterable<Cargo> arrivalCargos = cargoRepository.findAllById(arrivals);

    for (final Cargo cargo : departureCargos) {
      departureCargoCount += getTotalPieces(cargo.getBaggageList());
    }
    for (final Cargo cargo : arrivalCargos) {
      arrivalCargoCount += getTotalPieces(cargo.getBaggageList());
    }
    return new FlightResponse(departures.size(), arrivals.size(), departureCargoCount, arrivalCargoCount);
  }

  private int getTotalPieces(final List<Item> items) {
    return items.stream()
        .map(Item::getPieces)
        .mapToInt(Integer::intValue)
        .sum();
  }

  private double calcTotalWeightInKg(final List<Item> items) {
    return items.stream()
        .map(this::toKg)
        .mapToDouble(Double::doubleValue)
        .sum();
  }

  private double toKg(final Item item) {
    if (item.getWeightUnit() == WeightUnit.lb) {
      return item.getWeight() * 0.4535924;
    } else {
      return item.getWeight();
    }
  }

}
