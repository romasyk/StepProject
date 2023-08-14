package structure.dao;

import structure.model.Booking;
import structure.model.Flight;
import structure.model.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class BookingDaoImpl implements BookingDao{
private List<Booking> bookings;

    @Override
    public void create(List<User> users, Flight flight) {
        bookings.add(new Booking(flight,users));
    }

    @Override
    public List<Booking> getBookingsByUser(User user) {
        return bookings.stream()
                .filter(booking -> booking.getUser().stream()
                        .anyMatch(u -> u.getPassengerName().equals(user.getPassengerName())
                                && u.getPassengerSurname().equals(user.getPassengerSurname())))
                .collect(toList());
    }

    @Override
    public List<Booking> getBookings() {
        return bookings;
    }

    @Override
    public void removeBooking(int id) {
        this.bookings.remove(id);
    }
}
