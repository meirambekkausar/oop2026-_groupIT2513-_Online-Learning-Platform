package platform.repository;

import platform.config.DatabaseConfig;
import platform.entity.Lesson;

import java.sql.*;
import java.util.Optional;

public class LessonRepository {

    public Optional<Lesson> findById(long id) throws SQLException {
        String sql = "select * from lessons where id = ?";

        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, Math.toIntExact(id));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(new Lesson(
                        rs.getInt("id"),
                        rs.getInt("course_id"),
                        rs.getString("title")
                ));
            }
            return Optional.empty();
        }
    }

    public int countByCourse(int courseId) throws SQLException {
        String sql = "select count(*) from lessons where course_id = ?";

        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }
    public Lesson save(Long courseId, String title) throws SQLException {
        String sql = """
            INSERT INTO lessons(course_id, title)
            VALUES (?, ?)
            RETURNING id;
        """;

        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, courseId);
            ps.setString(2, title);

            ResultSet rs = ps.executeQuery();
            rs.next();

            return new Lesson(
                    rs.getLong("id"),
                    courseId,
                    title
            );
        }
    }
}
