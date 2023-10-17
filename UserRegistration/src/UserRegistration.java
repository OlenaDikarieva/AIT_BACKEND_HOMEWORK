import controllers.UsersController;
import models.User;
import repositories.CrudRepository;
import repositories.UserRepositoryFileImpl;
import services.UsersService;
import services.UsersServiceImpl;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class UserRegistration {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<User> users = new ArrayList<>();
        //repositories.CrudRepository repository = new repositories.UserRepositoryListImpl();
        CrudRepository repository = new UserRepositoryFileImpl("users.txt");

        UsersService usersService = new UsersServiceImpl(repository);
        UsersController usersController = new UsersController(scanner, usersService);
        usersController.addUser();
    }


}
