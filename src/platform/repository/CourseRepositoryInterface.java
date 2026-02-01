package platform.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CourseRepositoryInterface<T, ID> {

    Optional<T> findById(ID id) throws SQLException;

    List<T> findAll() throws SQLException;

    T save(T entity) throws SQLException;
}