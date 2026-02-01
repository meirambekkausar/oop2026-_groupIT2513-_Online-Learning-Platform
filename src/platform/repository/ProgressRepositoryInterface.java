package platform.repository;

import java.sql.SQLException;

public interface ProgressRepositoryInterface {
    void markCompleted(int userId, int lessonId) throws SQLException;
    int countCompleted(int userId, int courseId) throws SQLException;
}