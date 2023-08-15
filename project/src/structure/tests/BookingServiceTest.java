package structure.tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.dao.BookingDao;
import structure.model.*;
import structure.service.DefaultBookingService;
import structure.service.FlightsService;
import structure.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookingServiceTest {

    private DefaultBookingService bookingService;
    private BookingDao mockBookingDao;
    private UserService mockUserService;
    private FlightsService mockFlightsService;
    private User mockUser;
    private List<User> mockUsers;
    private List<Booking> mockBookings;

    @BeforeEach
    void setUp() {
        mockBookingDao = mock(BookingDao.class);
        mockUserService = mock(UserService.class);
        mockFlightsService = mock(FlightsService.class);
        bookingService = new DefaultBookingService(mockBookingDao, mockUserService, mockFlightsService);

        mockUser = new User("Alice", "Johnson");
        mockUsers = new ArrayList<>();
        mockUsers.add(mockUser);
        mockBookings = new ArrayList<>();
    }



    @Test
    void testGetFlightsByUser() {
        Flight flight1 = new Flight(PlaceOfDeparture.KYIV, "A23", LocalDateTime.of(2023, 11, 12, 12, 45), Destination.TOKIO, 150);
        Flight flight2 = new Flight(PlaceOfDeparture.KYIV, "D24", LocalDateTime.of(2023, 9, 1, 20, 00), Destination.BERLIN, 100);
        mockBookings.add(new Booking(flight1, mockUsers));
        mockBookings.add(new Booking(flight2, mockUsers));

        when(mockBookingDao.getBookingsByUser(mockUser)).thenReturn(mockBookings);

        List<Flight> userFlights = bookingService.getFlightsByUser(mockUser);

        assertEquals(2, userFlights.size());
        assertTrue(userFlights.contains(flight1));
        assertTrue(userFlights.contains(flight2));
    }

    @Test
    void testGetFlightsByUserNoBookings() {
        when(mockBookingDao.getBookingsByUser(mockUser)).thenReturn(new ArrayList<>());

        List<Flight> userFlights = bookingService.getFlightsByUser(mockUser);

        assertEquals(0, userFlights.size());
    }
}
