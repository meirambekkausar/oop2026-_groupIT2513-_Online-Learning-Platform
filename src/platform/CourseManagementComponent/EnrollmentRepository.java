package platform.CourseManagementComponent;

import platform.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentRepository implements EnrollmentRepositoryInterface {

    @Override
    public boolean exists(int userId, int courseId) throws SQLException {
        String sql = "SELECT 1 FROM enrollments WHERE user_id=? AND course_id=?";
        try (Connection c = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    @Override
    public void save(int userId, int courseId) throws SQLException {
        String sql = "INSERT INTO enrollments(user_id, course_id) VALUES (?, ?)";
        try (Connection c = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        }
    }
}