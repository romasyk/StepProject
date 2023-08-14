package structure.service;

import structure.model.Flight;

import java.util.List;

public interface FlightsServiceCollection {
    void displayFlightInfo(Flight flight);
    void flightsWithin24Hours();
    public void saveFlight(Flight flight);
    public List<Flight> getFlights();
    List<Flight> getMatchingFlights(String destinationInput, String dateInput, int passengers);
}
