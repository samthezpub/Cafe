package org.example.DAOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Getter
@AllArgsConstructor
public class PositionDAO {
    private final Connection connection;

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
}
