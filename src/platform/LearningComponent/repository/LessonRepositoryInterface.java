package platform.LearningComponent.repository;

import platform.LearningComponent.entity.Lesson;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface LessonRepositoryInterface {

    Optional<Lesson> findById(long id) throws SQLException;
    List<Lesson> findByCourseId(long courseId) throws SQLException;
    Lesson save(long courseId, String title, String type, String content) throws SQLException;
    int countByCourse(int courseId) throws SQLException;
}