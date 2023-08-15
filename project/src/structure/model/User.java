package structure.model;


import java.io.Serializable;

public class User implements Serializable {
    private String passengerName ;
    private String passengerSurname;
    public void name(){
        this.passengerName.toUpperCase().trim();
        this.passengerSurname.toUpperCase().trim();
    }

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
        this.passengerName = passengerName.toUpperCase().trim();
        this.passengerSurname = passengerSurname.toUpperCase().trim();
    }


    public boolean equals(User user) {
        return (user.getPassengerName().equals(this.passengerName)
                && user.getPassengerSurname().equals(this.passengerSurname));
    }

    public User() {
    }
}
