package structure.dao;

import structure.model.Booking;
import structure.model.Flight;
import structure.model.User;

import java.util.List;

public interface BookingDao {
  void create(List<User> users, Flight flight);
    List<Booking> getBookingsByUser(User user);
    List<Booking> getBookings();
    void removeBooking(int id);
}
