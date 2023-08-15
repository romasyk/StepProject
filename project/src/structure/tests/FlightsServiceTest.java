package structure.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.controller.FlightsController;
import structure.dao.FlightsDao;
import structure.model.Destination;
import structure.model.Flight;
import structure.model.PlaceOfDeparture;
import structure.service.FlightsService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlightsServiceTest {
    private FlightsService flightsServiceMock;

    @BeforeEach
    public void setup() {
        flightsServiceMock = mock(FlightsService.class);
    }

    @Test
    public void testFlightsWithin24Hours() {
        List<Flight> testFlights = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        testFlights.add(new Flight(PlaceOfDeparture.KYIV, "A23", now.plusHours(2), Destination.TOKIO, 150));
        testFlights.add(new Flight(PlaceOfDeparture.KYIV, "D24", now.plusHours(12), Destination.BERLIN, 100));
        testFlights.add(new Flight(PlaceOfDeparture.KYIV, "Q11", now.plusHours(25), Destination.TOKIO, 150));
        when(flightsServiceMock.getFlights()).thenReturn(testFlights);

        flightsServiceMock.flightsWithin24Hours();
    }
    @Test
    public void testDisplayFlightInfo() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 8, 15, 12, 0);
        Flight testFlight = new Flight(PlaceOfDeparture.KYIV, "A23", dateTime, Destination.TOKIO, 150);

        flightsServiceMock.displayFlightInfo(testFlight);

        System.setOut(System.out);
        String expectedOutput = "Місце вильоту: KYIV\n" +
                "Дата та час: 15-08-2023 12:00\n" +
                "Місце призначення: TOKIO\n" +
                "Кількість вільних місць: 150\n";

        assertEquals(expectedOutput, testFlight.toString());
    }
    @Test
    public void testGetMatchingFlights() {
        FlightsDao mockDao = mock(FlightsDao.class);
        FlightsService flightsService = new FlightsService(mockDao);
        Flight flight = new Flight(PlaceOfDeparture.KYIV, "A23", LocalDateTime.of(2023, 11, 12, 12, 45), Destination.TOKIO, 150);


        when(mockDao.getFlights()).thenReturn(List.of(flight));

        List<Flight> matchingFlights = flightsService.getMatchingFlights("TOKIO", "2023-11-12", 1);
        assertEquals(1, matchingFlights.size());
        assertEquals(flight.getId(), matchingFlights.get(0).getId());
    }

}