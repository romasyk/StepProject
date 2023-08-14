package structure.model;


public class User {
    private String passengerName;
    private String passengerSurname;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerSurname() {
        return passengerSurname;
    }

    public void setPassengerSurname(String passengerSurname) {
        this.passengerSurname = passengerSurname;
    }

    public User(String passengerName, String passengerSurname) {
        this.passengerName = passengerName;
        this.passengerSurname = passengerSurname;
    }


    public boolean equals(User user) {
        return (user.getPassengerName().equals(this.passengerName)
                && user.getPassengerSurname().equals(this.passengerSurname));
    }

    public User() {
    }
}
