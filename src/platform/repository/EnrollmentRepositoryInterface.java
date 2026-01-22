package platform.repository;

import java.sql.SQLException;

public interface EnrollmentRepositoryInterface {


    public boolean exists(int userId, int courseId) throws SQLException;

    public void save(int userId, int courseId) throws SQLException;
}

