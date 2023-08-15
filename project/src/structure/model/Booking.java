package structure.model;

import java.io.Serializable;
import java.util.List;

public class Booking implements Serializable {
    private Flight flight;
    private List<User> user;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public Booking(Flight flight, List<User> user) {
        this.flight = flight;
        this.user = user;
    }
}
