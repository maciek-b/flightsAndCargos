package com.mbogdanski.flightsandcargos.domain;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {

  @Id
  private Integer id;

  private Integer weight;

  private WeightUnit weightUnit;

  private Integer pieces;
}