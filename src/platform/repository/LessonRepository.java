package platform.repository;

import platform.config.DatabaseConfig;
import platform.entity.Lesson;
import platform.entity.LessonFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class LessonRepository implements LessonRepositoryInterface {

    @Override
    public Optional<Lesson> findById(long id) throws SQLException {
        String sql = "SELECT * FROM lessons WHERE id=?";
        try (Connection c = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String type = rs.getString("type");

                Object content;
                if ("video".equals(type)) {
                    content = rs.getString("video_url");
                } else if ("text".equals(type)) {
                    content = rs.getString("text_content");
                } else if ("quiz".equals(type)) {
                    Array quizArray = rs.getArray("quiz_questions");
                    if (quizArray == null) {
                        content = new String[0];
                    } else {
                        Object[] objArr = (Object[]) quizArray.getArray();
                        content = Arrays.stream(objArr)
                                .map(Object::toString)
                                .toArray(String[]::new);
                    }
                } else {
                    throw new SQLException("Unknown lesson type: " + type);
                }

                return Optional.of(
                        LessonFactory.createLesson(
                                type,
                                rs.getLong("id"),
                                rs.getLong("course_id"),
                                rs.getString("title"),
                                content
                        )
                );
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Lesson> findByCourseId(long courseId) throws SQLException {
        List<Lesson> list = new ArrayList<>();
        String sql = "SELECT * FROM lessons WHERE course_id=?";

        try (Connection c = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, courseId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String type = rs.getString("type");

                Object content;
                if ("video".equals(type)) {
                    content = rs.getString("video_url");
                } else if ("text".equals(type)) {
                    content = rs.getString("text_content");
                } else if ("quiz".equals(type)) {
                    Array quizArray = rs.getArray("quiz_questions");
                    if (quizArray == null) {
                        content = new String[0];
                    } else {
                        Object[] objArr = (Object[]) quizArray.getArray();
                        content = Arrays.stream(objArr)
                                .map(Object::toString)
                                .toArray(String[]::new);
                    }
                } else {
                    throw new SQLException("Unknown lesson type: " + type);
                }

                list.add(
                        LessonFactory.createLesson(
                                type,
                                rs.getLong("id"),
                                courseId,
                                rs.getString("title"),
                                content
                        )
                );
            }
        }
        return list;
    }

    @Override
    public Lesson save(long courseId, String title, String type, String content) throws SQLException {
        String sql = """
                INSERT INTO lessons(course_id, title, type, video_url, text_content, quiz_questions)
                VALUES (?, ?, ?, ?, ?, ?) RETURNING id
                """;

        try (Connection c = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, courseId);
            ps.setString(2, title);
            ps.setString(3, type);

            if ("video".equals(type)) {
                ps.setString(4, content);
                ps.setNull(5, Types.VARCHAR);
                ps.setNull(6, Types.ARRAY);
            } else if ("text".equals(type)) {
                ps.setNull(4, Types.VARCHAR);
                ps.setString(5, content);
                ps.setNull(6, Types.ARRAY);
            } else if ("quiz".equals(type)) {
                ps.setNull(4, Types.VARCHAR);
                ps.setNull(5, Types.VARCHAR);
                ps.setArray(6, c.createArrayOf("text", content.split(",")));
            } else {
                throw new SQLException("Unknown lesson type: " + type);
            }

            ResultSet rs = ps.executeQuery();
            rs.next();

            Object factoryContent =
                    "quiz".equals(type) ? content.split(",") : content;

            return LessonFactory.createLesson(
                    type,
                    rs.getLong(1),
                    courseId,
                    title,
                    factoryContent
            );
        }
    }

    @Override
    public int countByCourse(int courseId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM lessons WHERE course_id=?";
        try (Connection c = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }
}