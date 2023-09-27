package org.example.DAOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Getter
@AllArgsConstructor
public class CustomerDAO {
    private final Connection connection;

    public void editDiscount(Customer customer, double discount){
        String sql = "UPDATE client SET discount=? WHERE name=? AND phone=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setDouble(1, discount);
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getPhone());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteCustomer(int id){
        String sql = "DELETE FROM client WHERE id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
