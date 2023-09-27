package org.example.DAOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.Interfaces.IMenuItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Getter
@AllArgsConstructor
public class MenuItemDAO {
    private final Connection connection;

    public void addMenuItem(IMenuItem menuItem){
        String sql = "INSERT INTO menu (name,englishname,price) VALUES (?,?,?)";

        try(PreparedStatement preparedStatement =  connection.prepareStatement(sql);) {
            preparedStatement.setString(1, menuItem.getNameRussian());
            preparedStatement.setString(2, menuItem.getNameEnglish());
            preparedStatement.setDouble(3, menuItem.getPrice());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void editPrice(String name, double price){
        String sql = "UPDATE menu SET price=? WHERE name = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setDouble(1, price);
            preparedStatement.setString(2, name);


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteItem(String name){
        String sql = "DELETE FROM menu WHERE name=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, name);


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
