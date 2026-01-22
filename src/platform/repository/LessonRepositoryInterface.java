package platform.repository;

import platform.entity.Lesson;

import java.sql.SQLException;
import java.util.Optional;

public interface LessonRepositoryInterface {

    Optional<Lesson> findById(long id) throws SQLException;

    int countByCourse(int courseId) throws SQLException;

    Lesson save(Long courseId, String title) throws SQLException;
}

