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
    public void editTelAndEmail(Staff staff, String phone, String email){
        String sql = "UPDATE staff SET phone=?, email=? WHERE name=? AND position=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, phone);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, staff.getName());
            preparedStatement.setString(4, staff.getPosition().getName());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void editTel(Staff staff, String phone){
        String sql = "UPDATE staff SET phone=? WHERE name=? AND position=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, phone);
            preparedStatement.setString(2, staff.getName());
            preparedStatement.setString(3, staff.getPosition().getName());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteStaff(Staff staff){
        String sql = "DELETE FROM staff WHERE name=? AND phone=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getPhone());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStaff(int id){
        String sql = "DELETE FROM staff WHERE id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
