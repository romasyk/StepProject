package structure.service;

import structure.dao.FlightsDao;
import structure.model.Flight;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FlightsService implements FlightsServiceCollection {

    private FlightsDao flightsdao;
    public FlightsService(FlightsDao flightsdao) {
        this.flightsdao = flightsdao;
    }

    public  void displayFlightInfo(Flight flight) {
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

            System.out.println("Місце вильоту: " + flight.getDeparture());
            System.out.println("Дата та час: " + flight.getDateTime().format(dateTimeFormat));
            System.out.println("Місце призначення: " + flight.getDestination());
            System.out.println("Кількість вільних місць: " + flight.getSeats() + "\n");

    }
    public  void flightsWithin24Hours(){
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

}
