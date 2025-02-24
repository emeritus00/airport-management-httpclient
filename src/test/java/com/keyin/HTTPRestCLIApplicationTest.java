package com.keyin;

import com.keyin.domain.Airport;
import com.keyin.domain.Aircraft;
import com.keyin.domain.City;
import com.keyin.domain.Passenger;
import com.keyin.http.cli.HTTPRestCLIApplication;
import com.keyin.http.client.RESTClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class HTTPRestCLIApplicationTest {
    @Mock
    private RESTClient mockRESTClient;

    @Test
    public void testGenerateAirportReport() {
        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication();

        Airport stJohnsAirport = new Airport();
        stJohnsAirport.setCode("YYT");
        stJohnsAirport.setName("St. John's Airport");
        stJohnsAirport.setId(1);

        List<Airport> airportList = new ArrayList<>();
        airportList.add(stJohnsAirport);

        Mockito.when(mockRESTClient.getAllAirports()).thenReturn(airportList);

        httpRestCLIApplicationUnderTest.setRestClient(mockRESTClient);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateAirportReport().contains("YYT"));
    }

    @Test
    public void testGenerateAircraftReport() {
        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication();

        Aircraft aircraft = new Aircraft();
        aircraft.setType("Boeing 747");
        aircraft.setAirlineName("Air Canada");

        List<Aircraft> aircraftList = new ArrayList<>();
        aircraftList.add(aircraft);

        Mockito.when(mockRESTClient.getAllAircrafts()).thenReturn(aircraftList);

        httpRestCLIApplicationUnderTest.setRestClient(mockRESTClient);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateAircraftReport().contains("Boeing 747"));
    }

    @Test
    public void testGenerateCityReport() {
        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication();

        City city = new City();
        city.setName("St. John's");
        city.setState("NL");

        List<City> cityList = new ArrayList<>();
        cityList.add(city);

        Mockito.when(mockRESTClient.getAllCities()).thenReturn(cityList);

        httpRestCLIApplicationUnderTest.setRestClient(mockRESTClient);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateCityReport().contains("St. John's"));
    }

    @Test
    public void testGeneratePassengerReport() {
        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication();

        Passenger passenger = new Passenger();
        passenger.setFirstName("Adejoke");
        passenger.setLastName("Adewale");
        passenger.setPhoneNumber("709-456-7890");

        List<Passenger> passengerList = new ArrayList<>();
        passengerList.add(passenger);

        Mockito.when(mockRESTClient.getAllPassengers()).thenReturn(passengerList);

        httpRestCLIApplicationUnderTest.setRestClient(mockRESTClient);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generatePassengerReport().contains("Adejoke Adewale"));
    }

    @Test
    public void testGenerateAirportReportWithError() {
        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication();

        Mockito.when(mockRESTClient.getAllAirports()).thenReturn(new ArrayList<>());

        httpRestCLIApplicationUnderTest.setRestClient(mockRESTClient);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateAirportReport().isEmpty());
    }
}