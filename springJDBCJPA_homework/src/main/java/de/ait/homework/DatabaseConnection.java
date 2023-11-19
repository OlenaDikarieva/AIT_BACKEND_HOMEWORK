package de.ait.homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/Homework17jdbc";
        // http://www.gmail.com:8000/rrrr.txt
        String user = "root";
        String password = "F@r1469uk@";

        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных");
        }

    }
}


