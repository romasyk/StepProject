package structure.dao;

import structure.model.Booking;
import structure.model.Flight;
import structure.model.User;

import java.io.*;
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
    public void loadBookings(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Bookings.txt"));
            oos.writeObject(bookings);
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    @Override
    public void downloadBookings() {
        try {
            this.bookings = (List<Booking>) new ObjectInputStream(new FileInputStream("Bookings.txt")).readObject();
        } catch (FileNotFoundException e){
            System.out.println("Файл не знайдено");
        }catch (EOFException e){
            System.out.println("Даних немає");
        } catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Booking> getBookings() {
        return bookings;
    }

    @Override
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public void removeBooking(int id) {
        this.bookings.remove(id);
    }
}
