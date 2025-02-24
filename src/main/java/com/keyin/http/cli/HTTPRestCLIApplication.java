package com.keyin.http.cli;

import com.keyin.domain.Airport;
import com.keyin.domain.Aircraft;
import com.keyin.domain.City;
import com.keyin.domain.Passenger;
import com.keyin.http.client.RESTClient;

import java.util.List;
import java.util.Scanner;

public class HTTPRestCLIApplication {

    private RESTClient restClient;

    public String generateAirportReport() {
        List<Airport> airports = getRestClient().getAllAirports();

        StringBuilder report = new StringBuilder();

        for (Airport airport : airports) {
            report.append(airport.getName())
                    .append(" - ")
                    .append(airport.getCode())
                    .append("\n");
        }

        System.out.println(report.toString());
        return report.toString();
    }

    public String generateAircraftReport() {
        List<Aircraft> aircrafts = getRestClient().getAllAircrafts();

        StringBuilder report = new StringBuilder();

        for (Aircraft aircraft : aircrafts) {
            report.append(aircraft.getType())
                    .append(" - ")
                    .append(aircraft.getAirlineName())
                    .append("\n");
        }

        System.out.println(report.toString());
        return report.toString();
    }

    public String generateCityReport() {
        List<City> cities = getRestClient().getAllCities();

        StringBuilder report = new StringBuilder();

        for (City city : cities) {
            report.append(city.getName())
                    .append(" - ")
                    .append(city.getState())
                    .append("\n");
        }

        System.out.println(report.toString());
        return report.toString();
    }

    public String generatePassengerReport() {
        List<Passenger> passengers = getRestClient().getAllPassengers();

        StringBuilder report = new StringBuilder();

        for (Passenger passenger : passengers) {
            report.append(passenger.getFirstName())
                    .append(" ")
                    .append(passenger.getLastName())
                    .append(" - ")
                    .append(passenger.getPhoneNumber())
                    .append("\n");
        }

        System.out.println(report.toString());
        return report.toString();
    }

    public String generateAirportsInCitiesReport() {
        List<City> cities = getRestClient().getAllCities();

        StringBuilder report = new StringBuilder();

        for (City city : cities) {
            report.append("City: ")
                    .append(city.getName())
                    .append(" - ")
                    .append(city.getState())
                    .append("\n");

            List<Airport> airports = city.getAirports();
            if (airports != null && !airports.isEmpty()) {
                for (Airport airport : airports) {
                    report.append("  Airport: ")
                            .append(airport.getName())
                            .append(" - ")
                            .append(airport.getCode())
                            .append("\n");
                }
            } else {
                report.append("  No airports found for this city.\n");
            }
        }

        System.out.println(report.toString());
        return report.toString();
    }

    public String generateAircraftPassengersReport() {
        List<Passenger> passengers = getRestClient().getAllPassengers();

        StringBuilder report = new StringBuilder();

        for (Passenger passenger : passengers) {
            report.append("Passenger: ")
                    .append(passenger.getFirstName())
                    .append(" ")
                    .append(passenger.getLastName())
                    .append("\n");

            List<Aircraft> aircrafts = passenger.getAircraft();
            if (aircrafts != null && !aircrafts.isEmpty()) {
                for (Aircraft aircraft : aircrafts) {
                    report.append("  Aircraft: ")
                            .append(aircraft.getType())
                            .append(" - ")
                            .append(aircraft.getAirlineName())
                            .append("\n");
                }
            } else {
                report.append("  No aircraft found for this passenger.\n");
            }
        }

        System.out.println(report.toString());
        return report.toString();
    }

    public String generateAirportsForAircraftReport() {
        List<Aircraft> aircrafts = getRestClient().getAllAircrafts();

        StringBuilder report = new StringBuilder();

        for (Aircraft aircraft : aircrafts) {
            report.append("Aircraft: ")
                    .append(aircraft.getType())
                    .append(" - ")
                    .append(aircraft.getAirlineName())
                    .append("\n");

            // Assuming aircraft has a list of airports it can operate from
            List<Airport> airports = aircraft.getAirports();
            if (airports != null && !airports.isEmpty()) {
                for (Airport airport : airports) {
                    report.append("  Airport: ")
                            .append(airport.getName())
                            .append(" - ")
                            .append(airport.getCode())
                            .append("\n");
                }
            } else {
                report.append("  No airports found for this aircraft.\n");
            }
        }

        System.out.println(report.toString());
        return report.toString();
    }

    public String generateAirportsUsedByPassengersReport() {
        List<Passenger> passengers = getRestClient().getAllPassengers();

        StringBuilder report = new StringBuilder();

        for (Passenger passenger : passengers) {
            report.append("Passenger: ")
                    .append(passenger.getFirstName())
                    .append(" ")
                    .append(passenger.getLastName())
                    .append("\n");

            City city = passenger.getCity();
            if (city != null) {
                List<Airport> airports = city.getAirports();
                if (airports != null && !airports.isEmpty()) {
                    for (Airport airport : airports) {
                        report.append("  Airport: ")
                                .append(airport.getName())
                                .append(" - ")
                                .append(airport.getCode())
                                .append("\n");
                    }
                } else {
                    report.append("  No airports found for this city.\n");
                }
            } else {
                report.append("  No city found for this passenger.\n");
            }
        }

        System.out.println(report.toString());
        return report.toString();
    }

    private void listGreetings() {
        System.out.println(getRestClient().getResponseFromHTTPRequest());
    }

    public RESTClient getRestClient() {
        if (restClient == null) {
            restClient = new RESTClient();
        }

        return restClient;
    }

    public void setRestClient(RESTClient restClient) {
        this.restClient = restClient;
    }

    public static void main(String[] args) {
        // Default server URL
        String serverURL = "http://localhost:8080";

        // Override the default URL if a command-line argument is provided
        if (args.length > 0) {
            serverURL = args[0];
        }

        System.out.println("Using server URL: " + serverURL);

        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication();

        if (serverURL != null && !serverURL.isEmpty()) {
            RESTClient restClient = new RESTClient();
            restClient.setServerURL(serverURL);

            cliApp.setRestClient(restClient);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. List Airports");
                System.out.println("2. List Aircrafts");
                System.out.println("3. List Cities");
                System.out.println("4. List Passengers");
                System.out.println("5. Get Greeting");
                System.out.println("6. List Airports in Cities");
                System.out.println("7. List Aircraft Passengers Have Traveled On");
                System.out.println("8. List Airports for Aircraft");
                System.out.println("9. List Airports Used by Passengers");
                System.out.println("10. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        cliApp.generateAirportReport();
                        break;
                    case 2:
                        cliApp.generateAircraftReport();
                        break;
                    case 3:
                        cliApp.generateCityReport();
                        break;
                    case 4:
                        cliApp.generatePassengerReport();
                        break;
                    case 5:
                        cliApp.listGreetings();
                        break;
                    case 6:
                        cliApp.generateAirportsInCitiesReport();
                        break;
                    case 7:
                        cliApp.generateAircraftPassengersReport();
                        break;
                    case 8:
                        cliApp.generateAirportsForAircraftReport();
                        break;
                    case 9:
                        cliApp.generateAirportsUsedByPassengersReport();
                        break;
                    case 10:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Error: Server URL is empty. Please provide a valid server URL.");
        }
    }
}