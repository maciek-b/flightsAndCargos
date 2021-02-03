package com.mbogdanski.flightsandcargos.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "cargos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Cargo {

  @Id
  private Integer flightId;

  @JsonProperty("baggage")
  private List<Item> baggageList;

  @JsonProperty("cargo")
  private List<Item> cargoList;
}