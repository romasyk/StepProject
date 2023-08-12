package structure.dao;

import structure.model.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightsDao {
    public static List<Flight> flightList = new ArrayList<>();
    public List<Flight> getAllFlights() {
        return flightList;
    }
}
