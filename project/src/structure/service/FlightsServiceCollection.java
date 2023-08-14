package structure.service;

import structure.model.Flight;

public interface FlightsServiceCollection {
    void displayFlightInfo(Flight flight);
    void flightsWithin24Hours();
}
