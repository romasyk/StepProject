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
    public void saveFlight(Flight flight){
        this.flightList.add(flight);
    }
    public List<Flight> getFlights(){
        return this.flightList;
    }
    public void saveDataLocally(List<Flight> flightList) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("flights.txt"))) {
            objectOutputStream.writeObject(flightList);
        } catch (IOException e) {
            System.out.println("Failed to save data locally: " + e.getMessage());
        }
    }

    public List<Flight> loadLocalData() {
        List<Flight> loadedFlights = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("flights.txt"))) {
            loadedFlights = (List<Flight>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load data from the local file: " + e.getMessage());
        }
        return loadedFlights;
    }

}