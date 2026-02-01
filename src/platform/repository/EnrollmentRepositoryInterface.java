package platform.repository;

import java.sql.SQLException;

public interface EnrollmentRepositoryInterface {
    boolean exists(int userId, int courseId) throws SQLException;
    void save(int userId, int courseId) throws SQLException;
}