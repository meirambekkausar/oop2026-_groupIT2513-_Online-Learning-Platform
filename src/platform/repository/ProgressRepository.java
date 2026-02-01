package platform.repository;

import platform.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgressRepository implements ProgressRepositoryInterface {

    @Override
    public void markCompleted(int userId, int lessonId) throws SQLException {
        String sql = """
            INSERT INTO progress(user_id, lesson_id, completed)
            VALUES (?, ?, true)
            ON CONFLICT (user_id, lesson_id)
            DO UPDATE SET completed = true
        """;
        try (Connection c = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, lessonId);
            ps.executeUpdate();
        }
    }

    @Override
    public int countCompleted(int userId, int courseId) throws SQLException {
        String sql = """
            SELECT COUNT(*) FROM progress p
            JOIN lessons l ON p.lesson_id = l.id
            WHERE p.user_id=? AND l.course_id=? AND p.completed=true
        """;
        try (Connection c = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }
}