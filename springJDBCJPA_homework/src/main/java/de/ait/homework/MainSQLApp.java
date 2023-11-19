package de.ait.homework;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainSQLApp {
    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        List<Animal> personList = new ArrayList<>();

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();

            String createTableSQL = "CREATE TABLE IF NOT EXISTS animal_table (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "type VARCHAR(255) NOT NULL, " +
                    "breed VARCHAR(255) NOT NULL, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "age INT NOT NULL)";

            statement.executeUpdate(createTableSQL);

            String insertSQL1 = "INSERT INTO animal_table (type,breed,name,age) VALUES ('Cat','British cat','Barsik', 1)";
            String insertSQL2 = "INSERT INTO animal_table (type,breed,name,age) VALUES ('Dog','Shih tzu','Second', 2)";

            statement.executeUpdate(insertSQL1);
            statement.executeUpdate(insertSQL2);

            System.out.println("Таблица создана и записи добавлены успешно");


            resultSet = statement.executeQuery("SELECT * FROM animal_table ");

            while (resultSet.next()) {
                //System.out.println(resultSet.getString("name") + " " + resultSet.getInt("age"));
                personList.add(new Animal(resultSet.getString("type"),resultSet.getString("breed"),resultSet.getString("name"),resultSet.getInt("age")));

            }

        } catch (SQLException e) {e.printStackTrace(); }

// =============== распечатаем нашу коллекцию =======

        System.out.println(personList);
    }
}
