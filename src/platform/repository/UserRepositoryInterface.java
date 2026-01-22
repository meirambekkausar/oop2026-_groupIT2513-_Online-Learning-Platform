package platform.repository;

import platform.entity.User;
import java.sql.SQLException;

public interface UserRepositoryInterface {

    public User save(String name, String email) throws SQLException;
}
