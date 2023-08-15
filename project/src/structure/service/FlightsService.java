package structure.service;

import structure.dao.FlightsDao;
import structure.model.Flight;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FlightsService implements FlightsServiceCollection {

    private FlightsDao flightsdao;

    public FlightsService(FlightsDao flightsdao) {
        this.flightsdao = flightsdao;
    }

    public void displayFlightInfo(Flight flight) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        System.out.println("Місце вильоту: " + flight.getDeparture());
        System.out.println("Дата та час: " + flight.getDateTime().format(dateTimeFormat));
        System.out.println("Місце призначення: " + flight.getDestination());
        System.out.println("Кількість вільних місць: " + flight.getSeats() + "\n");
    }

    public void flightsWithin24Hours() {
        List<Flight> flightList = flightsdao.getAllFlights();

        System.out.println("Available flights departing from Kyiv in the next 24 hours:");

        LocalDateTime next24Hours = LocalDateTime.now().plusHours(24);
        boolean anyFlightsFound = false;

        for (Flight flight : flightList) {

            if (flight.getDateTime().isAfter(LocalDateTime.now())
                    && flight.getDateTime().isBefore(next24Hours)) {
                displayFlightInfo(flight);
                anyFlightsFound = true;
            }
        }

        if (!anyFlightsFound) {
            System.out.println("No flights departing from Kyiv in the next 24 hours.");
        }
    }

    public void saveFlight(Flight flight) {
        flightsdao.saveFlight(flight);
    }


    public List<Flight> getMatchingFlights(String destinationInput, String dateInput, int passengers) {
        List<Flight> matchingFlights = getFlights().stream()
                .filter(flight -> flight.getDestination().toString().equalsIgnoreCase(destinationInput))
                .filter(flight -> flight.getDateTime().toLocalDate().equals(LocalDate.parse(dateInput)))
                .filter(flight -> flight.getSeats() >= passengers)
                .toList();

        if (matchingFlights.isEmpty()) {
            System.out.println("No flights available for the specified criteria.");
        } else {
            for (int i = 0; i < matchingFlights.size(); i++) {
                System.out.println("# " + (i + 1) + ":");
                displayFlightInfo(matchingFlights.get(i));
            }
        }
        return matchingFlights;
    }

    public List<Flight> getFlights() {
        return flightsdao.getFlights();
    }
    public void saveData() {
        List<Flight> flightList = getFlights();
        flightsdao.saveDataLocally(flightList);
    }

    public void loadData() {
        List<Flight> loadedFlights = flightsdao.loadLocalData();
        if (!loadedFlights.isEmpty()) {
            flightsdao.flightList = loadedFlights;
        }
    }
}