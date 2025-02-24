package com.keyin.http.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.domain.Airport;
import com.keyin.domain.Aircraft;
import com.keyin.domain.City;
import com.keyin.domain.Passenger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class RESTClient {
    private String serverURL;
    private HttpClient client;

    public String getResponseFromHTTPRequest() {
        String responseBody = "";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverURL)).build();

        try {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Status Code: " + response.statusCode());
            }

            responseBody = response.body();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseBody;
    }

    public List<Airport> getAllAirports() {
        return fetchDataFromEndpoint("/airports", new TypeReference<List<Airport>>() {});
    }

    public List<Aircraft> getAllAircrafts() {
        return fetchDataFromEndpoint("/aircrafts", new TypeReference<List<Aircraft>>() {});
    }

    public List<City> getAllCities() {
        return fetchDataFromEndpoint("/cities", new TypeReference<List<City>>() {});
    }

    public List<Passenger> getAllPassengers() {
        return fetchDataFromEndpoint("/passengers", new TypeReference<List<Passenger>>() {});
    }

    private <T> List<T> fetchDataFromEndpoint(String endpoint, TypeReference<List<T>> typeReference) {
        List<T> data = new ArrayList<>();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(serverURL + endpoint))
                    .GET()
                    .build();

            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                data = buildListFromResponse(response.body(), typeReference);
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public <T> List<T> buildListFromResponse(String response, TypeReference<List<T>> typeReference) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.readValue(response, typeReference);
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public HttpClient getClient() {
        if (client == null) {
            client = HttpClient.newHttpClient();
        }

        return client;
    }
}