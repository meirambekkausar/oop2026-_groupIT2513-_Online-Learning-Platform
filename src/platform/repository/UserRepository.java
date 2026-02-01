package platform.repository;

import platform.config.DatabaseConfig;
import platform.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements UserRepositoryInterface {

    @Override
    public User save(String name, String email) throws SQLException {
        String sql = "INSERT INTO users(name, email) VALUES (?, ?) RETURNING id";
        try (Connection c = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new User(rs.getLong("id"), name, email);
        }
    }
}