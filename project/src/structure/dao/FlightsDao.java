package structure.dao;

import structure.model.Flight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FlightsDao implements FlightsDaoCollection {
    public  List<Flight> flightList = new ArrayList<>();
    public List<Flight> getAllFlights() {
        return flightList;
    }
    public  void saveFlights(List<Flight> flightList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("flights.txt"))) {
            outputStream.writeObject(flightList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  List<Flight> loadFlights() {
        List<Flight> flightList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("flights.txt"))) {
            flightList = (List<Flight>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return flightList;
    }
    public void saveFlight(Flight flight){
        this.flightList.add(flight);
    }
    public List<Flight> getFlights(){
        return this.flightList;
    }
}
