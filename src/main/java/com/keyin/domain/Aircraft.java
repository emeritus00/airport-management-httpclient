package com.keyin.domain;

import java.util.List;
import java.util.Objects;

public class Aircraft {
    private Long id;

    private String type;
    private String airlineName;
    private int numberOfPassengers;
    private List<Passenger> passengers;

    public Aircraft(){

    }

    public Aircraft(Long id) {
        this.id = id;
    }

    public Aircraft(Long id, String type, String airlineName, int numberOfPassengers, List<Passenger> passengers) {
        this.id = id;
        this.type = type;
        this.airlineName = airlineName;
        this.numberOfPassengers = numberOfPassengers;
        this.passengers = passengers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aircraft aircraft = (Aircraft) o;
        return Objects.equals(id, aircraft.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public List<Airport> getAirports() {
        List<Airport> airports = List.of();
        return airports; // Return the list of airports
    }

    
}
