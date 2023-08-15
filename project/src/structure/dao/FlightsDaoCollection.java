package structure.dao;

import structure.model.Flight;
import java.util.List;

public interface FlightsDaoCollection {
    List<Flight> getAllFlights();
    void saveFlight(Flight flight);
    List<Flight> getFlights();
    void saveDataLocally(List<Flight> flightList);
    List<Flight> loadLocalData();

}