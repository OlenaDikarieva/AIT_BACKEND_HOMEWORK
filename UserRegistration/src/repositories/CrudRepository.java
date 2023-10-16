package repositories;

import models.User;

import java.util.List;

public interface CrudRepository {
    void save(User user);

    User findByID(Long id);

    User findByEmail(String email);

    List<User> findAll();

    void deleteById(Long id);

    void update (User user);
}

