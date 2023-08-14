package structure.dao;

import structure.model.Flight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FlightsDao implements FlightsDaoCollection {
    public static List<Flight> flightList = new ArrayList<>();
    public List<Flight> getAllFlights() {
        return flightList;
    }
    public static void saveFlights(List<Flight> flightList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream())) {
            outputStream.writeObject(flightList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Flight> loadFlights() {
        List<Flight> flightList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream())) {
            flightList = (List<Flight>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return flightList;
    }
}
