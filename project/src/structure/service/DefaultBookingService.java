package structure.service;

import structure.dao.BookingDao;
import structure.model.Booking;
import structure.model.Flight;
import structure.model.User;

import java.util.ArrayList;
import java.util.List;

public class DefaultBookingService implements BookingService{
    private BookingDao bookingDao;
    private UserService userService;
    private FlightsService flightsService;


    @Override
    public List<Flight> getFlightsByUser(User user) {
        List<Flight> userFlights = new ArrayList<>();
        bookingDao.getBookingsByUser(user).forEach(e->userFlights.add(e.getFlight()));
        return userFlights;
    }

    @Override
    public List<Booking> getBookingsByUser(User user) {
        return bookingDao.getBookingsByUser(user);
    }
    @Override
    public void create(List<User> users, Flight flight){
        bookingDao.create(users,flight);
    }

    @Override
    public List<Booking> getBookings() {
        return bookingDao.getBookings();
    }

    @Override
    public void removeBooking(int id) {
        bookingDao.removeBooking(id);
    }

    @Override
    public void downloadBookings() {
        bookingDao.downloadBookings();
    }

    @Override
    public void loadBookings() {
bookingDao.loadBookings();
    }

    public DefaultBookingService(BookingDao bookingDao, UserService userService, FlightsService flightsService) {
        this.bookingDao = bookingDao;
        this.userService = userService;
        this.flightsService = flightsService;
    }
}
