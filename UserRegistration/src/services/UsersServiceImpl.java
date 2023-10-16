package services;
import models.User;
import repositories.CrudRepository;

import java.util.List;

public class UsersServiceImpl implements UsersService{
    private final CrudRepository usersRepository;

    public UsersServiceImpl(CrudRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User addUser(String name, String email, String password) {

        if (name == null || name.equals("") || name.equals(" ")) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (email == null || email.equals("") || email.equals(" ")) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (password == null || password.equals("") || password.equals(" ")) {
        }

        User existedUser = usersRepository.findByEmail(email);

        if (existedUser != null) {
            throw new IllegalArgumentException("Пользователь с таким email уже есть");
        }

        User user = new User(name,email, password);

        usersRepository.save(user);

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }
}
