
package repositories;

import models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryFileImpl implements CrudRepository {
    private String fileName;
    private  Long currentID=1L;

    public UserRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User user) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))) {
            user.setId(currentID++);
            writer.write(user.getId()+";"+user.getName()+";"+user.getEmail()+";"+ user.getPassword()); // 1;jack;jack@email.com
            writer.newLine();

        } catch (IOException e) {
            System.out.println("file save error");;
        }
    }

    private static void saveUsersToFile(List<User> users, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : users) {
                writer.write(user.getId()+";"+user.getName() + ";" + user.getEmail()+";"+user.getPassword());
                writer.newLine(); // Move to a new line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByID(Long id) {
        List<User> users=findAll();
        return users.stream()
                .filter(u->u.getId()== id)
                .findFirst()
                .get();
    }

    @Override
    public User findByEmail(String email) {
        List<User> users=findAll();
        return users.stream()
                .filter(u->u.getEmail()== email)
                .findFirst()
                .get();
    }

    @Override
    public List<User> findAll() {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            return reader.lines()
                    .map(l->l.split(";"))
                    .map(uArr->new User(Long.parseLong(uArr[0]),uArr[1],uArr[2])).collect(Collectors.toList());
                 //   toList();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(User user) {

    }
}

