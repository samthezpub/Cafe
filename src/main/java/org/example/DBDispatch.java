package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DBDispatch {
    private static DBDispatch INSTANCE;

    private Connection connection;

    private String url;
    private String username;
    private String password;

    private DBDispatch() {
        loadConfig();
        connectDatabase();
    }

    public static DBDispatch getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DBDispatch();
        }
        return INSTANCE;
    }

    private void loadConfig() {
        Properties properties = new Properties();
        try {
            FileInputStream input = new FileInputStream("src/main/resources/db.properties");
            properties.load(input);
            input.close();

            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectDatabase() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addOrder(Order order) {
        try {

            String sql = "INSERT INTO \"order\" (\"menuItem\", price, date) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,order.getMenuItem().getNameRussian());
            preparedStatement.setDouble(2,order.getMenuItem().getPrice());
            preparedStatement.setDate(3,order.getTime());


            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
