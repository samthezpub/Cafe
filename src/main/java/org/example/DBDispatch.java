package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public List<String> getPositions() {
        String sql = "SELECT name FROM positions";
        ResultSet resultSet;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();


            List<String > list = new ArrayList<>();
            while (resultSet.next()){
                list.add(resultSet.getString("name"));
            }

            return list;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPosition(String position) {
        String sql = "INSERT INTO positions (name) VALUES (?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, position);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void addOrder(Order order) {
        try {

            String sql = "INSERT INTO \"order\" (\"menuItem\", price, date) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, order.getMenuItem().getNameRussian());
            preparedStatement.setDouble(2, order.getMenuItem().getPrice());
            preparedStatement.setDate(3, order.getTime());


            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editOrderById(Order order, int id) {
        try {
            String sql = "UPDATE \"order\" SET \"menuItem\"=?, price=?, date=? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, order.getMenuItem().getNameRussian());
            preparedStatement.setDouble(2, order.getMenuItem().getPrice());
            preparedStatement.setDate(3, order.getTime());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
