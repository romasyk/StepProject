package structure.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.model.Destination;
import structure.model.Flight;
import structure.model.PlaceOfDeparture;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FlightsDaoTest {
    private FlightsDao flightsDao;

    @BeforeEach
    public void setUp() {
        flightsDao = new FlightsDao();
    }

    @Test
    public void testSaveFlight() {

        Flight mockFlight = new Flight(PlaceOfDeparture.KYIV, "A23", LocalDateTime.of(2023, 11, 12, 12, 45), Destination.TOKIO, 150);

        flightsDao.saveFlight(mockFlight);

        assertEquals(1, flightsDao.getFlights().size());
        assertEquals(mockFlight, flightsDao.getFlights().get(0));
    }

    @Test
    public void testGetAllFlights() {

        Flight mockFlight1 = new Flight(PlaceOfDeparture.KYIV, "A23", LocalDateTime.of(2023, 11, 12, 12, 45), Destination.TOKIO, 150);
        Flight mockFlight2 = new Flight(PlaceOfDeparture.KYIV, "D24", LocalDateTime.of(2023, 9, 1, 20, 00), Destination.BERLIN, 100);
        flightsDao.saveFlight(mockFlight1);
        flightsDao.saveFlight(mockFlight2);

        List<Flight> allFlights = flightsDao.getAllFlights();

        assertEquals(2, allFlights.size());
        assertEquals(mockFlight1, allFlights.get(0));
        assertEquals(mockFlight2, allFlights.get(1));
    }

    @Test
    public void testSaveDataLocally() {
        Flight mockFlight1 = new Flight(PlaceOfDeparture.KYIV, "A23", LocalDateTime.of(2023, 11, 12, 12, 45), Destination.TOKIO, 150);
        Flight mockFlight2 = new Flight(PlaceOfDeparture.KYIV, "D24", LocalDateTime.of(2023, 9, 1, 20, 00), Destination.BERLIN, 100);
        List<Flight> mockFlightList = new ArrayList<>();
        mockFlightList.add(mockFlight1);
        mockFlightList.add(mockFlight2);

        flightsDao.saveDataLocally(mockFlightList);
        List<Flight> loadedFlights = flightsDao.loadLocalData();

        assertEquals(mockFlightList.size(), loadedFlights.size());
        assertEquals(mockFlightList.get(0).toString(), loadedFlights.get(0).toString());
        assertEquals(mockFlightList.get(1).toString(), loadedFlights.get(1).toString());
    }

    @Test
    public void testLoadLocalDataFileNotFound() {
        List<Flight> loadedFlights = flightsDao.loadLocalData();

        assertNotEquals(0, loadedFlights.size());
    }
}
