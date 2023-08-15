package structure.controller;

import structure.model.User;
import structure.service.UserService;

import java.util.Scanner;

public class UserController {
    Scanner scanner = new Scanner(System.in);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
