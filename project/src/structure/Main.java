package structure;

import structure.controller.FlightsController;

import structure.dao.FlightsDao;
import structure.service.FlightsService;


public class Main {
    public static void main(String[] args) {
        FlightsDao flightsdao = new FlightsDao();
        FlightsService flightsService = new FlightsService(flightsdao);
        FlightsController flightsController = new FlightsController(flightsService);


        flightsController.menu();

    }

}