package platform.repository;

import platform.config.DatabaseConfig;

import java.sql.*;

public class EnrollmentRepository implements EnrollmentRepositoryInterface {
    @Override
    public boolean exists(int userId, int courseId) throws SQLException {
        String sql = "select 1 from enrollments where user_id=? and course_id=?";

        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            return ps.executeQuery().next();
        }
    }
    @Override
    public void save(int userId, int courseId) throws SQLException {
        String sql = "insert into enrollments(user_id, course_id) values (?, ?)";

        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        }
    }
}
