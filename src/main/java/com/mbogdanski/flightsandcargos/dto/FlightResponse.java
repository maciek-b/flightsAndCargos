package com.mbogdanski.flightsandcargos.dto;

import lombok.Data;

@Data
public class FlightResponse {
  final int departingFlights;
  final int arrivingFlights;
  final int departingBaggage;
  final int arrivingBaggage;
}
