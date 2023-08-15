package structure.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.dao.BookingDao;
import structure.dao.BookingDaoImpl;
import structure.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingDaoTest {

    private BookingDao bookingDao;
    private List<Booking> mockBookings;
    private User mockUser;
    private Flight mockFlight;

    @BeforeEach
    void setUp() {
        bookingDao = new BookingDaoImpl();
        mockBookings = new ArrayList<>();
        mockUser = new User("Alice", "Johnson");
        mockFlight = new Flight(PlaceOfDeparture.KYIV, "A23", LocalDateTime.of(2023, 11, 12, 12, 45), Destination.TOKIO, 150);

        bookingDao.setBookings(mockBookings);
    }

    @Test
    void testCreateBooking() {
        bookingDao.create(List.of(mockUser), mockFlight);
        assertEquals(1, mockBookings.size());
        Booking booking = mockBookings.get(0);
        assertEquals(mockFlight, booking.getFlight());
        assertEquals(List.of(mockUser), booking.getUser());
    }

    @Test
    void testGetBookingsByUser() {
        Booking booking = new Booking(mockFlight, List.of(mockUser));
        mockBookings.add(booking);

        List<Booking> userBookings = bookingDao.getBookingsByUser(mockUser);
        assertEquals(1, userBookings.size());
        assertEquals(booking, userBookings.get(0));
    }
}
