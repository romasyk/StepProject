package structure.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Flight implements Serializable {
    private String id;
    private LocalDateTime dateTime;
    private Destination destination;
    private PlaceOfDeparture departure;
    private int seats;

    public Flight(PlaceOfDeparture departure, String id, LocalDateTime dateTime, Destination destination, int seats) {
        this.departure = departure;
        this.id = id;
        this.dateTime = dateTime;
        this.destination = destination;
        this.seats = seats;
    }

    public PlaceOfDeparture getDeparture() {
        return departure;
    }

    public void setDeparture(PlaceOfDeparture departure) {
        this.departure = departure;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Місце вильоту: " + departure + "\n" +
                "Дата та час: " + dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "\n" +
                "Місце призначення: " + destination + "\n" +
                "Кількість вільних місць: " + seats + "\n";
    }
}