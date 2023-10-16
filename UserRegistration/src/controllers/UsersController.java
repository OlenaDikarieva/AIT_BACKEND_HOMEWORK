package controllers;

import models.User;
import services.UsersService;

import java.util.List;
import java.util.Scanner;

public class UsersController {

    private final Scanner scanner;
    private final UsersService usersService;

    public UsersController(Scanner scanner, UsersService usersService) {
        this.scanner = scanner;
        this.usersService = usersService;
    }

    public void addUser() {
        String name;
        while (true) {
            System.out.print("Enter name (or 'exit' to quit): ");
            name = scanner.nextLine();

            if (name.equalsIgnoreCase("exit")) {
                break;
            }
        }

        String email;
        do {
            System.out.println("Enter your email (check @ and not null): ");
            email = scanner.nextLine();
        } while (!isValidEmail(email));


        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        // close scanner
        scanner.close();

        User user = usersService.addUser(name, email, password);
        System.out.println(user);

    }

    public void getAllUsers() {
        List<User> users = usersService.getAllUsers();
        System.out.println(users);
    }
    public static boolean isValidEmail(String email) {
        return email != null && !email.isEmpty() && email.contains("@");
    }
}