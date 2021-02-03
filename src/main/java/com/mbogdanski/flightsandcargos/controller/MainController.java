package com.mbogdanski.flightsandcargos.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbogdanski.flightsandcargos.dto.FlightResponse;
import com.mbogdanski.flightsandcargos.dto.WeightResponse;
import com.mbogdanski.flightsandcargos.service.MainService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class MainController {

  private final MainService mainService;

  @GetMapping("weight")
  public WeightResponse weight(@RequestParam(value = "flightNumber") final Integer flightNumber,
                               @RequestParam(value = "date")
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate date) {

    return mainService.calcWeight(flightNumber, date);
  }

  @GetMapping("flight")
  public FlightResponse flight(@RequestParam(value = "code") final String airportCode,
                               @RequestParam(value = "date")
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate date) {

    return mainService.getFlightsInfo(airportCode, date);
  }
}
