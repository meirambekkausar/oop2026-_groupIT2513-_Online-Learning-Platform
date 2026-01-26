package platform.repository;

import platform.config.DatabaseConfig;
import platform.entity.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements CourseRepositoryInterface {
    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT id, title, archived FROM courses";

        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                courses.add(new Course(
                        (int) rs.getLong("id"),
                        rs.getString("title"),
                        rs.getBoolean("archived")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return courses;
    }
    @Override
    public Course findById(int id) throws SQLException {
        String sql = "select * from courses where id = ?";

        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getBoolean("archived")
                );
            }
            return null;
        }
    }
    @Override
    public Course save(String title) throws SQLException {
        String sql = """
            INSERT INTO courses(title, archived)
            VALUES (?, false)
            RETURNING id;
        """;    

        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, title);

            ResultSet rs = ps.executeQuery();
            rs.next();

            return new Course(
                    (int) rs.getLong("id"),
                    title,
                    false
            );
        }
    }
}
