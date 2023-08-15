package structure.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import structure.controller.BookingController;
import structure.model.Destination;
import structure.model.Flight;
import structure.model.PlaceOfDeparture;
import structure.model.User;
import structure.service.BookingService;
import structure.service.FlightsService;
import structure.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class BookingControllerTest {
    private BookingService bookingService;
    private FlightsService flightsService;
    private UserService userService;
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        bookingService = Mockito.mock(BookingService.class);
        flightsService = Mockito.mock(FlightsService.class);
        userService = Mockito.mock(UserService.class);
        bookingController = new BookingController(bookingService, userService, flightsService);
    }

    @Test
    void testCancelBooking() {
        int id = 1;
        Mockito.when(bookingService.getBookings().size()).thenReturn(2);

        bookingController.cancelBooking();

        Mockito.verify(bookingService, times(1)).removeBooking(id);
    }

    @Test
    void testMyFlights() {
        String name = "John";
        String surname = "Doe";
        User user = new User(name, surname);

        Flight flight1 = new Flight(PlaceOfDeparture.KYIV, "A23", LocalDateTime.of(2023, 11, 12, 12, 45), Destination.TOKIO, 150);
        Flight flight2 = new Flight(PlaceOfDeparture.KYIV, "D24", LocalDateTime.of(2023, 9, 1, 20, 00), Destination.BERLIN, 100);
        List<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);

        Mockito.when(userService.getUserByName(name, surname)).thenReturn(user);
        Mockito.when(bookingService.getFlightsByUser(user)).thenReturn(flights);

        bookingController.myFlights();

        Mockito.verify(flightsService, times(2)).displayFlightInfo(any());
    }

    @Test
    void testReservation() {
        Scanner scannerMock = Mockito.mock(Scanner.class);
        Mockito.when(scannerMock.nextLine()).thenReturn("TOKIO", "2023-08-16", "2");
        Mockito.when(scannerMock.nextInt()).thenReturn(1);

        User user = new User("John", "Doe");
        Mockito.when(userService.createUser("John", "Doe")).thenReturn(user);

        Flight flight1 = new Flight(PlaceOfDeparture.KYIV, "D24", LocalDateTime.of(2023, 9, 1, 20, 00), Destination.BERLIN, 100);
        List<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        Mockito.when(flightsService.getMatchingFlights("TOKIO", "2023-08-16", 2)).thenReturn(flights);

        Mockito.doNothing().when(userService).saveUser(user);
        Mockito.doNothing().when(bookingService).create(Mockito.anyList(), Mockito.any(Flight.class));

        Mockito.doNothing().when(flight1).setSeats(Mockito.anyInt());

        bookingController.reservation();

        Mockito.verify(scannerMock, Mockito.times(3)).nextLine();
        Mockito.verify(scannerMock, Mockito.times(1)).nextInt();
        Mockito.verify(userService, Mockito.times(2)).createUser("John", "Doe");
        Mockito.verify(userService, Mockito.times(2)).saveUser(user);
        Mockito.verify(bookingService, Mockito.times(1)).create(Mockito.anyList(), Mockito.any(Flight.class));
        Mockito.verify(flightsService, Mockito.times(1)).displayFlightInfo(flight1);
        Mockito.verify(flight1, Mockito.times(1)).setSeats(Mockito.anyInt());
    }


}

