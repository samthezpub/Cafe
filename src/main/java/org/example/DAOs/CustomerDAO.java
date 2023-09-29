package org.example.DAOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.Customer;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class CustomerDAO {
    private final Connection connection;

    public void editDiscount(Customer customer, double discount) {
        String sql = "UPDATE client SET discount=? WHERE name=? AND phone=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setDouble(1, discount);
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getPhone());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCustomer(int id) {
        String sql = "DELETE FROM client WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double getMinDiscount() {
        String sql = "SELECT MIN(discount) FROM client";

        double result = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            {
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    result = resultSet.getDouble(1); // Используйте индекс колонки (1), а не имя колонки ("discount")
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public double getMaxDiscount() {
        String sql = "SELECT MAX(discount) FROM client";

        double result = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            {
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    result = resultSet.getDouble(1); // Используйте индекс колонки (1), а не имя колонки ("discount")
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public List<Customer> getMinDiscountClients() {
        double minDiscount = getMinDiscount();
        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM client WHERE discount=?";
        ResultSet resultSet;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, minDiscount);

            resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    customers.add(
                            new Customer(
                                    resultSet.getString("name"),
                                    resultSet.getString("birthdate"),
                                    resultSet.getString("phone"),
                                    resultSet.getString("email"),
                                    resultSet.getDouble("discount")
                            )
                    );
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return customers;
    }

    public List<Customer> getMaxDiscountClients() {
        double minDiscount = getMaxDiscount();
        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM client WHERE discount=?";
        ResultSet resultSet;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, minDiscount);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getString("name"),
                                resultSet.getString("birthdate"),
                                resultSet.getString("phone"),
                                resultSet.getString("email"),
                                resultSet.getDouble("discount")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return customers;
    }
    public double getAverageDiscount(){
        String sql = "SELECT AVG(discount) FROM client";
        double result = -1;

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
          ResultSet resultSet =  preparedStatement.executeQuery();

          if (resultSet.next()){
              result = resultSet.getDouble(1);
          }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Customer getMaxAgeClient(){
        String sql = "SELECT * FROM client WHERE birthdate = (SELECT MIN(birthdate) FROM client)";
        Customer result = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet =  preparedStatement.executeQuery();

            if (resultSet.next()){
                result = new Customer(
                        resultSet.getString("name"),
                        resultSet.getString("birthdate"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getDouble("discount")
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Customer getMinAgeClient(){
        String sql = "SELECT * FROM client WHERE birthdate = (SELECT MAX(birthdate) FROM client)";
        Customer result = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet =  preparedStatement.executeQuery();

            if (resultSet.next()){
                result = new Customer(
                        resultSet.getString("name"),
                        resultSet.getString("birthdate"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getDouble("discount")
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Customer getBirthdayTodayClient(){
        // Получаем текущую дату
        LocalDate currentDate = LocalDate.now();

        // Задаем формат даты
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Форматируем дату в строку
        String formattedDate = currentDate.format(formatter);

        String sql = "SELECT * FROM client WHERE birthdate = ?";
        Customer result = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, formattedDate);
            ResultSet resultSet =  preparedStatement.executeQuery();

            if (resultSet.next()){
                result = new Customer(
                        resultSet.getString("name"),
                        resultSet.getString("birthdate"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getDouble("discount")
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
    public List<Customer> getWithoutEmailClients(){
        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM client WHERE email IS NULL ";
        ResultSet resultSet;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getString("name"),
                                resultSet.getString("birthdate"),
                                resultSet.getString("phone"),
                                resultSet.getString("email"),
                                resultSet.getDouble("discount")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return customers;
    }
};
