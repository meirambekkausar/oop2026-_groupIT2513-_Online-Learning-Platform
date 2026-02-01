package platform.repository;

import platform.entity.User;
import java.sql.SQLException;

public interface UserRepositoryInterface {
    User save(String name, String email) throws SQLException;
}