package com.mbogdanski.flightsandcargos.controller;

import static com.mbogdanski.flightsandcargos.TestConstants.AIRPORT;
import static com.mbogdanski.flightsandcargos.TestConstants.DATE;
import static com.mbogdanski.flightsandcargos.TestConstants.DEPARTING_FLIGHT;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mbogdanski.flightsandcargos.dto.FlightResponse;
import com.mbogdanski.flightsandcargos.dto.WeightResponse;
import com.mbogdanski.flightsandcargos.service.MainService;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

  @Mock
  private MainService mainService;
  @InjectMocks
  private MainController mainController;

  @Test
  public void should_calc_weight() {
    when(mainService.calcWeight(DEPARTING_FLIGHT.getFlightNumber(), DATE)).thenReturn(new WeightResponse(1, 2));

    mainController.weight(DEPARTING_FLIGHT.getFlightNumber(), DATE);

    verify(mainService).calcWeight(DEPARTING_FLIGHT.getFlightNumber(), DATE);
  }

  @Test
  public void should_get_flight_info() {
    when(mainService.getFlightsInfo(AIRPORT, DATE)).thenReturn(new FlightResponse(1, 2, 3, 4));

    mainController.flight(AIRPORT, DATE);

    verify(mainService).getFlightsInfo(AIRPORT, DATE);
  }
}
