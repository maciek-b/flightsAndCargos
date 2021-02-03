package com.mbogdanski.flightsandcargos;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbogdanski.flightsandcargos.domain.Cargo;
import com.mbogdanski.flightsandcargos.domain.Flight;
import com.mbogdanski.flightsandcargos.repository.CargoRepository;
import com.mbogdanski.flightsandcargos.repository.FlightRepository;
import lombok.AllArgsConstructor;

@Component
@Profile("!test")
@AllArgsConstructor
public class DbSeeder implements CommandLineRunner {
  private final FlightRepository flightRepository;
  private final CargoRepository cargoRepository;

  @Override
  public void run(String... strings) throws IOException {

    flightRepository.deleteAll();
    cargoRepository.deleteAll();

    final InputStream flightsJson = Flight.class.getResourceAsStream("/static/flights.json");
    final List<Flight> flights = new ObjectMapper().readValue(flightsJson, new TypeReference<>() {
    });
    final InputStream cargosJson = Flight.class.getResourceAsStream("/static/cargos.json");
    final List<Cargo> cargos = new ObjectMapper().readValue(cargosJson, new TypeReference<>() {
    });

    flightRepository.saveAll(flights);
    cargoRepository.saveAll(cargos);
  }
}

