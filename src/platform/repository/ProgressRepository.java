package platform.repository;

import platform.config.DatabaseConfig;

import java.sql.*;

public class ProgressRepository {

    public void markCompleted(int userId, int lessonId) throws SQLException {
        String sql = """
            insert into progress(user_id, lesson_id, completed)
            values (?, ?, true)
            on conflict (user_id, lesson_id)
            do update set completed = true
        """;

        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, lessonId);
            ps.executeUpdate();
        }
    }

    public int countCompleted(int userId, int courseId) throws SQLException {
        String sql = """
            select count(*) from progress p
            join lessons l on p.lesson_id = l.id
            where p.user_id=? and l.course_id=? and p.completed=true
        """;

        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }
}
