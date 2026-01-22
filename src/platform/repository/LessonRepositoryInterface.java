package platform.repository;

import platform.entity.Lesson;

import java.sql.SQLException;
import java.util.Optional;

public interface LessonRepositoryInterface {

    public Optional<Lesson> findById(long id) throws SQLException;

    public int countByCourse(int courseId) throws SQLException;

    public Lesson save(Long courseId, String title) throws SQLException;
}

