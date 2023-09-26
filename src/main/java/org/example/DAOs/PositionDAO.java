package org.example.DAOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.DBDispatch;
import org.example.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class PositionDAO {
    private Connection connection;

    public void addPosition(Position position) {
        String sql = "INSERT INTO positions (name) VALUES (?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, position.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> getPositions() {
        String sql = "SELECT * FROM positions";
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
}
