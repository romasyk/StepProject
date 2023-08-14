package structure.controller;

import structure.service.BookingService;
import structure.service.FlightsService;
import structure.service.UserService;
import structure.utils.ConsoleUtil;

import java.util.Scanner;

public class BookingController {
    Scanner scanner = new Scanner(System.in);
private BookingService bookingService;
private FlightsService flightsService;
private UserService userService;
private FlightsController flightsController;
private UserController userController;

public void cancelBooking(){
    int id = ConsoleUtil.getInputNumberValue(scanner,"Введіть номер бронювання: ", "Це не число!!!");
    if(id>=bookingService.getBookings().size()) {
        System.out.println("Такого бронювання немає!!!");
    }else {
        bookingService.removeBooking(id);
    }
}

public void myFlights(){
    String name = ConsoleUtil.getInputName(scanner);
    String surname = ConsoleUtil.getInputSurname(scanner);

    bookingService.getFlightsByUser(userService.getUserByName(name,surname))
            .forEach(e -> flightsService.displayFlightInfo(e));
}

public void reservation(){

//    bookingService.create();
}

}
