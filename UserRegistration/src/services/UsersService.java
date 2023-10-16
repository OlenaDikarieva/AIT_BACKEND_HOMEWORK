package services;
import models.User;
import java.util.List;

public interface UsersService {
    User addUser(String name,String email, String password);
    List<User> getAllUsers();
}
