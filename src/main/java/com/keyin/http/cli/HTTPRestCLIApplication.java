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

        StringBuffer report = new StringBuffer();

        for (Airport airport : airports) {
            report.append(airport.getName());
            report.append(" - ");
            report.append(airport.getCode());

            if (airports.indexOf(airport) != (airports.size() - 1)) {
                report.append(",");
            }
        }

        System.out.println(report.toString());

        return report.toString();
    }

    public String generateAircraftReport() {
        List<Aircraft> aircrafts = getRestClient().getAllAircrafts();

        StringBuffer report = new StringBuffer();

        for (Aircraft aircraft : aircrafts) {
            report.append(aircraft.getType());
            report.append(" - ");
            report.append(aircraft.getAirlineName());

            if (aircrafts.indexOf(aircraft) != (aircrafts.size() - 1)) {
                report.append(",");
            }
        }

        System.out.println(report.toString());

        return report.toString();
    }

    public String generateCityReport() {
        List<City> cities = getRestClient().getAllCities();

        StringBuffer report = new StringBuffer();

        for (City city : cities) {
            report.append(city.getName());
            report.append(" - ");
            report.append(city.getState());

            if (cities.indexOf(city) != (cities.size() - 1)) {
                report.append(",");
            }
        }

        System.out.println(report.toString());

        return report.toString();
    }

    public String generatePassengerReport() {
        List<Passenger> passengers = getRestClient().getAllPassengers();

        StringBuffer report = new StringBuffer();

        for (Passenger passenger : passengers) {
            report.append(passenger.getFirstName());
            report.append(" ");
            report.append(passenger.getLastName());
            report.append(" - ");
            report.append(passenger.getPhoneNumber());

            if (passengers.indexOf(passenger) != (passengers.size() - 1)) {
                report.append(",");
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
                System.out.println("6. Exit");
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