package structure.controller;

import structure.model.Flight;
import structure.model.User;
import structure.service.BookingService;
import structure.service.FlightsService;
import structure.service.UserService;
import structure.utils.ConsoleUtil;
import structure.utils.ConsoleUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingController {
    Scanner scanner = new Scanner(System.in);
    private BookingService bookingService;
    private FlightsService flightsService;
    private UserService userService;

    public BookingController(BookingService bookingService,  UserService userService, FlightsService flightsService) {
        this.bookingService = bookingService;
        this.flightsService = flightsService;
        this.userService = userService;
    }

    public void cancelBooking() {
        int id = ConsoleUtil.getInputNumberValue(scanner, "Введіть номер бронювання: ", "Це не число!!!") - 1 ;
        if (id  >= bookingService.getBookings().size()) {
            System.out.println("Такого бронювання немає!!!");
        } else {
            try {
                bookingService.removeBooking(id);
            }catch (IndexOutOfBoundsException e){
                System.out.println("Такого бронювання немає!!!");
            }

        }
    }


    public void myFlights() {
        String name = ConsoleUtil.getInputName(scanner);
        String surname = ConsoleUtil.getInputSurname(scanner);

        bookingService.getFlightsByUser(userService.getUserByName(name, surname))
                .forEach(e -> flightsService.displayFlightInfo(e));
    }


    public void reservation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter destination: ");
        String destinationInput = scanner.nextLine().trim();


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        boolean validDate = false;

        String dateInput = null;
        while (!validDate) {
            try {
                System.out.println("Enter date (yyyy-MM-dd): ");
                dateInput = scanner.nextLine().trim();

                dateFormat.setLenient(false);
                dateFormat.parse(dateInput);

                validDate = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter a valid date in yyyy-MM-dd format.");
            }
        }


        System.out.println("Enter number of passengers: ");
        int passengers = ConsoleUtils.getInputNumberValue(scanner, "It's not a number");

        System.out.println("Available flights matching your criteria:");

        List<Flight> flightsForReservation = flightsService.getMatchingFlights(destinationInput, dateInput, passengers);

        int number = 0;
        boolean isFalse = false;
        while (!isFalse) {
            number = ConsoleUtil.getInputNumberValue(scanner, "Введіть номер рейсу для бронювання: ", "Це не число!!!");
            if (number >= flightsForReservation.size() && number <= 0) {
                continue;
            } else {
                isFalse = true;
            }
        }

        List<User> usersBooking = new ArrayList<>();
        for (int i = 0; i < passengers; i++) {
            String name = ConsoleUtil.getInputName(scanner);
            String surname = ConsoleUtil.getInputSurname(scanner);
            User user = userService.createUser(name, surname);
            usersBooking.add(user);
            userService.saveUser(user);
        }

        bookingService.create(usersBooking, flightsForReservation.get(number - 1));
        flightsForReservation.get(number - 1).setSeats(flightsForReservation.get(number - 1).getSeats() - passengers);

    }
    public void downloadData(){
        userService.downloadUsers();
        bookingService.downloadBookings();
    }

    public void saveData(){
        userService.loadData();
        bookingService.loadBookings();
    }

}
