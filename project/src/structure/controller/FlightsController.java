package structure.controller;

import structure.model.Destination;
import structure.model.Flight;
import structure.model.PlaceOfDeparture;
import structure.service.FlightsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class FlightsController {
    public static FlightsService flightsService;


    public FlightsController(FlightsService flightsService) {
        FlightsController.flightsService = flightsService;
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
    public static void flightsWithin24Hours(){
        flightsService.flightsWithin24Hours();
    }
    public void saveData() {
        flightsService.saveData();
    }

    public void loadData() {
        flightsService.loadData();
    }
//    public static void savedFlights() {
//        Flight flight1 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "A23",
//                LocalDateTime.of(2023, 11, 12, 12, 45),
//                Destination.TOKIO,
//                150
//        );
//
//        Flight flight2 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "D24",
//                LocalDateTime.of(2023, 9, 1, 20, 0),
//                Destination.BERLIN,
//                100
//        );
//
//        Flight flight3 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "Q11",
//                LocalDateTime.of(2023, 8, 13, 15, 30),
//                Destination.TOKIO,
//                150
//        );
//
//        Flight flight4 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "B44",
//                LocalDateTime.of(2023, 10, 5, 8, 15),
//                Destination.LONDON,
//                200
//        );
//
//        Flight flight5 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "F12",
//                LocalDateTime.of(2023, 9, 18, 9, 30),
//                Destination.PARIS,
//                180
//        );
//
//        Flight flight6 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "G29",
//                LocalDateTime.of(2023, 11, 30, 16, 0),
//                Destination.BERLIN,
//                120
//        );
//
//        Flight flight7 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "H37",
//                LocalDateTime.of(2023, 12, 25, 13, 45),
//                Destination.SINGAPORE,
//                200
//        );
//
//        Flight flight8 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "C14",
//                LocalDateTime.of(2023, 9, 9, 19, 0),
//                Destination.LONDON,
//                180
//        );
//
//        Flight flight9 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "E25",
//                LocalDateTime.of(2023, 10, 10, 10, 30),
//                Destination.TOKIO,
//                160
//        );
//
//        Flight flight10 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "F49",
//                LocalDateTime.of(2023, 8, 22, 14, 15),
//                Destination.BERLIN,
//                120
//        );
//
//        Flight flight11 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "Z02",
//                LocalDateTime.of(2023, 11, 5, 9, 0),
//                Destination.BEIJING,
//                180
//        );
//
//        Flight flight12 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "Y14",
//                LocalDateTime.of(2023, 9, 27, 17, 30),
//                Destination.MEXICO,
//                220
//        );
//
//        Flight flight13 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "X18",
//                LocalDateTime.of(2023, 10, 15, 18, 45),
//                Destination.ROME,
//                190
//        );
//
//        Flight flight14 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "W29",
//                LocalDateTime.of(2023, 8, 8, 11, 0),
//                Destination.DUBLIN,
//                160
//        );
//
//        Flight flight15 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "V32",
//                LocalDateTime.of(2023, 9, 10, 13, 15),
//                Destination.WARSAW,
//                140
//        );
//
//        Flight flight16 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "U49",
//                LocalDateTime.of(2023, 10, 21, 15, 30),
//                Destination.ISTANBUL,
//                200
//        );
//
//        Flight flight17 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "T50",
//                LocalDateTime.of(2023, 11, 28, 8, 0),
//                Destination.SIDNEY,
//                180
//        );
//
//        Flight flight18 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "S25",
//                LocalDateTime.of(2023, 9, 13, 19, 45),
//                Destination.BEIJING,
//                160
//        );
//
//        Flight flight19 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "R15",
//                LocalDateTime.of(2023, 10, 16, 21, 0),
//                Destination.MEXICO,
//                220
//        );
//
//        Flight flight20 = new Flight(
//                PlaceOfDeparture.KYIV,
//                "Q05",
//                LocalDateTime.of(2023, 8, 25, 10, 30),
//                Destination.ROME,
//                190
//        );
//
//        flightsService.saveFlight(flight1);
//        flightsService.saveFlight(flight2);
//        flightsService.saveFlight(flight3);
//        flightsService.saveFlight(flight4);
//        flightsService.saveFlight(flight5);
//        flightsService.saveFlight(flight6);
//        flightsService.saveFlight(flight7);
//        flightsService.saveFlight(flight8);
//        flightsService.saveFlight(flight9);
//        flightsService.saveFlight(flight10);
//        flightsService.saveFlight(flight11);
//        flightsService.saveFlight(flight12);
//        flightsService.saveFlight(flight13);
//        flightsService.saveFlight(flight14);
//        flightsService.saveFlight(flight15);
//        flightsService.saveFlight(flight16);
//        flightsService.saveFlight(flight17);
//        flightsService.saveFlight(flight18);
//        flightsService.saveFlight(flight19);
//        flightsService.saveFlight(flight20);
//        flightsService.saveData();
//
//
//    }


}