package structure.controller;

import structure.model.Destination;
import structure.model.Flight;
import structure.model.PlaceOfDeparture;
import structure.service.FlightsService;

import structure.utils.ConsoleUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;






import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import static structure.dao.FlightsDao.flightList;



public class FlightsController {
    private static FlightsService flightsService;


    public FlightsController(FlightsService flightsService) {
        FlightsController.flightsService = flightsService;
    }

    public static void menu() {
        Flight flight1 = new Flight(PlaceOfDeparture.KYIV, "A23", LocalDateTime.of(2023, 11, 12, 12, 45), Destination.TOKIO, 150);
        Flight flight2 = new Flight(PlaceOfDeparture.KYIV, "D24", LocalDateTime.of(2023, 9, 1, 20, 00), Destination.BERLIN, 100);
        Flight flight3 = new Flight(PlaceOfDeparture.KYIV, "Q11", LocalDateTime.of(2023, 8, 13, 15, 30), Destination.TOKIO, 150);
        Flight flight4 = new Flight(PlaceOfDeparture.KYIV, "A23", LocalDateTime.of(2023, 11, 12, 20, 00), Destination.TOKIO, 150);

        flightsService.saveFlight(flight1);
        flightsService.saveFlight(flight2);
        flightsService.saveFlight(flight3);
        flightsService.saveFlight(flight4);


        flightList.add(flight1);
        flightList.add(flight2);
        flightList.add(flight3);

        Scanner scanner = new Scanner(System.in);

        int option = scanner.nextInt();
        switch (option) {
            case 1 -> flightsService.flightsWithin24Hours();
            case 2 -> findFlightById();
            case 3 -> findFlightByDetails();
            default -> System.out.println("This option doesn't exist");
        }

    }

    public static void findFlightById() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the flight ID (or write 'exit' to quit): ");
            String userInput = scanner.nextLine().trim().toUpperCase();

            if (userInput.equals("EXIT")) {
                break;
            }

            boolean flightFound = false;

            for (int i = 0; i < flightsService.getFlights().size(); i++) {
                Flight flight = flightsService.getFlights().get(i);


            for (int i = 0; i < flightsService.getFlights().size(); i++) {
                Flight flight = flightsService.getFlights().get(i);

            for (int i = 0; i < flightList.size(); i++) {
                Flight flight = flightList.get(i);

                if (flight.getId().equals(userInput)) {
                    System.out.println("Flight " + (i + 1) + ":");
                    flightsService.displayFlightInfo(flight);
                    flightFound = true;
                }
            }

            if (!flightFound) {
                System.out.println("There is no such flight");
            }
        }
    }

    public static void findFlightByDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter destination: ");
        String destinationInput = scanner.nextLine().trim();

        System.out.println("Enter date (yyyy-MM-dd): ");
        String dateInput = scanner.nextLine().trim();



        System.out.println("Enter number of passengers: ");
        int passengers = ConsoleUtils.getInputNumberValue(scanner,  "It's not a number");

        System.out.println("Available flights matching your criteria:");

        List<Flight> a = flightsService.getMatchingFlights(destinationInput, dateInput, passengers);

    }


        System.out.println("Enter number of passengers: ");
        int passengers = scanner.nextInt();

        System.out.println("Available flights matching your criteria:");


        flightList.stream()
                        .filter(flight -> flight.getDestination().toString()
                        .equalsIgnoreCase(destinationInput))
                        .filter(flight -> flight.getDateTime()
                        .toLocalDate().equals(LocalDate.parse(dateInput)))
                        .filter(flight -> flight.getSeats() >= passengers)
                        .forEach(flightsService::displayFlightInfo);
    }




}
