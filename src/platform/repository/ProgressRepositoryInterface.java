package platform.repository;

import java.sql.SQLException;

public interface ProgressRepositoryInterface {

    public void markCompleted(int userId, int lessonId) throws SQLException;

    public int countCompleted(int userId, int courseId) throws SQLException;
}
