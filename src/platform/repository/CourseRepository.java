package platform.repository;

import platform.config.DatabaseConfig;
import platform.entity.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository implements CourseRepositoryInterface<Course, Long> {

    @Override
    public Optional<Course> findById(Long id) throws SQLException {
        String sql = "SELECT * FROM courses WHERE id=?";
        try (Connection c = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(new Course.Builder(rs.getString("title"))
                        .id((int) rs.getLong("id"))
                        .build());
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Course> findAll() throws SQLException {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Connection c = DatabaseConfig.getInstance().getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Course.Builder(rs.getString("title"))
                        .id((int) rs.getLong("id"))
                        .build());
            }
        }
        return list;
    }

    @Override
    public Course save(Course course) throws SQLException {
        String sql = "INSERT INTO courses(title, archived) VALUES (?, ?) RETURNING id";
        try (Connection c = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, course.getTitle());
            ps.setBoolean(2, false);

            ResultSet rs = ps.executeQuery();
            rs.next();

            return new Course.Builder(course.getTitle())
                    .id((int) rs.getLong("id"))
                    .build();
        }
    }
}