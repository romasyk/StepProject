package structure.service;

import structure.model.Booking;
import structure.model.Flight;
import structure.model.User;

import java.util.List;

public interface BookingService {
    void create(List<User> users, Flight flight);
    List<Flight> getFlightsByUser(User user);
    List<Booking> getBookingsByUser(User user);
    List<Booking> getBookings();
    void removeBooking(int id);
}
