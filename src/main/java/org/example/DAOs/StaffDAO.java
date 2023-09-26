package org.example.DAOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Getter
@AllArgsConstructor
public class StaffDAO {
    private final Connection connection;

    public void addStaff(Staff staff){
        String sql = "INSERT INTO staff (name,phone,email,position) VALUES (?,?,?,?)";

        try(PreparedStatement preparedStatement =  connection.prepareStatement(sql);) {
           preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getPhone());
            preparedStatement.setString(3, staff.getEmail());
            preparedStatement.setString(4, staff.getPosition().getName());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
