package com.mbogdanski.flightsandcargos.dto;

import lombok.Data;

@Data
public class WeightResponse {
  final double cargoWeight;
  final double baggageWeight;
  final double totalWeight;

  public WeightResponse(final double cargoWeight, final double baggageWeight) {
    this.cargoWeight = cargoWeight;
    this.baggageWeight = baggageWeight;
    this.totalWeight = cargoWeight + baggageWeight;
  }
}
