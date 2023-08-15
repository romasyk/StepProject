package structure;

import structure.controller.BookingController;
import structure.controller.FlightsController;
import structure.controller.UserController;
import structure.dao.*;
import structure.service.*;

import java.util.Scanner;

public class ConsoleApp {
    private final Scanner scanner;


    FlightsDao flightsdao = new FlightsDao();
    FlightsService flightsService = new FlightsService(flightsdao);
    FlightsController flightsController = new FlightsController(flightsService);

    UserDao userDao = new CollectionsUserDao();
    UserService userService = new DefaultUserService(userDao);
    UserController userController = new UserController(userService);

    BookingDao bookingDao = new BookingDaoImpl();
    BookingService bookingService = new DefaultBookingService(bookingDao, userService, flightsService);
    BookingController bookingController = new BookingController(bookingService, userService, flightsService);


    public ConsoleApp() {
        scanner = new Scanner(System.in);
    }

    public void run() {
       downloadData();


        boolean isRunning = true;
        while (isRunning) {
            showMainMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1 -> showFlightsTable();
                case 2 -> showFlightDetails();
                case 3 -> searchAndBookFlight();
                case 4 -> cancelReservation();
                case 5 -> showUserFlights();
                case 0 -> {
                    saveData();
                    isRunning = false;
                }
                default -> System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private void showMainMenu() {
        System.out.println("========== ГОЛОВНЕ МЕНЮ ==========");
        System.out.println("1. Онайн-табло");
        System.out.println("2. Подивитися інформацію про рейс");
        System.out.println("3. Пошук та бронювання рейсу");
        System.out.println("4. Скасувати бронювання");
        System.out.println("5. Мої рейси");
        System.out.println("0. Вихід");
        System.out.println("===================================");
        System.out.print("Виберіть опцію: ");
    }

    private int getUserChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private void showFlightsTable() {
        // TODO: метод для виведення онлайн-табло рейсів
    }

    private void showFlightDetails() {
        // TODO: метод для виведення деталей рейсу
    }

    private void searchAndBookFlight() {
      bookingController.reservation();
    }

    private void cancelReservation() {
        bookingController.cancelBooking();
    }

    private void showUserFlights() {
        bookingController.myFlights();
    }

    private void downloadData(){
        bookingController.downloadData();
    }

    public void saveData(){
        bookingController.saveData();
    }
}

