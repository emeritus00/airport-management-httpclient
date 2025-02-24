package com.keyin.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.keyin.domain.Airport;
import com.keyin.domain.Aircraft;
import com.keyin.domain.City;
import com.keyin.domain.Passenger;
import com.keyin.http.client.RESTClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RESTClientTest {

    @Test
    public void testBuildAirportListFromResponse() throws JsonProcessingException {
        String jsonResponse = "[{\"id\": 1,\"name\": \"St. John's\",\"code\": \"YYT\"}," +
                "{\"id\": 2,\"name\": \"Deer Lake\",\"code\": \"YDF\"}]";

        RESTClient restClientUnderTest = new RESTClient();

        List<Airport> airportList = restClientUnderTest.buildListFromResponse(
                jsonResponse,
                new TypeReference<List<Airport>>() {}
        );

        Assertions.assertTrue(airportList.contains(new Airport("YYT")));
        Assertions.assertTrue(airportList.contains(new Airport("YDF")));
    }

    @Test
    public void testBuildAircraftListFromResponse() throws JsonProcessingException {
        String jsonResponse = "[{\"id\": 1,\"type\": \"Boeing 747\",\"airlineName\": \"Air Canada\"}," +
                "{\"id\": 2,\"type\": \"Airbus A320\",\"airlineName\": \"WestJet\"}]";

        RESTClient restClientUnderTest = new RESTClient();

        List<Aircraft> aircraftList = restClientUnderTest.buildListFromResponse(
                jsonResponse,
                new TypeReference<List<Aircraft>>() {}
        );

        Assertions.assertEquals(2, aircraftList.size());
        Assertions.assertEquals("Boeing 747", aircraftList.get(0).getType());
    }

    @Test
    public void testBuildCityListFromResponse() throws JsonProcessingException {
        String jsonResponse = "[{\"id\": 1,\"name\": \"St. John's\",\"state\": \"NL\"}," +
                "{\"id\": 2,\"name\": \"Toronto\",\"state\": \"ON\"}]";

        RESTClient restClientUnderTest = new RESTClient();

        List<City> cityList = restClientUnderTest.buildListFromResponse(
                jsonResponse,
                new TypeReference<List<City>>() {}
        );

        Assertions.assertEquals(2, cityList.size());
        Assertions.assertEquals("St. John's", cityList.get(0).getName());
    }

    @Test
    public void testBuildPassengerListFromResponse() throws JsonProcessingException {
        String jsonResponse = "[{\"id\": 1,\"firstName\": \"Adejoke\",\"lastName\": \"Adewale\",\"phoneNumber\": \"123-456-7890\"}," +
                "{\"id\": 2,\"firstName\": \"Jane\",\"lastName\": \"Smith\",\"phoneNumber\": \"987-654-3210\"}]";

        RESTClient restClientUnderTest = new RESTClient();

        List<Passenger> passengerList = restClientUnderTest.buildListFromResponse(
                jsonResponse,
                new TypeReference<List<Passenger>>() {}
        );

        Assertions.assertEquals(2, passengerList.size());
        Assertions.assertEquals("Adejoke Adewale", passengerList.get(0).getFirstName() + " " + passengerList.get(0).getLastName());
    }

    @Test
    public void testBuildListFromResponseWithInvalidJson() {
        String invalidJsonResponse = "Invalid JSON";

        RESTClient restClientUnderTest = new RESTClient();

        Assertions.assertThrows(JsonProcessingException.class, () -> {
            restClientUnderTest.buildListFromResponse(
                    invalidJsonResponse,
                    new TypeReference<List<Airport>>() {}
            );
        });
    }
}