package platform.repository;

import platform.entity.Lesson;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface LessonRepositoryInterface {

    /**
     * Find a lesson by its ID
     */
    Optional<Lesson> findById(long id) throws SQLException;

    /**
     * Get all lessons for a specific course
     */
    List<Lesson> findByCourseId(long courseId) throws SQLException;

    /**
     * Save a new lesson for a course
     *
     * @param courseId ID of the course
     * @param title    Lesson title
     * @param type     Lesson type: "video", "text", "quiz"
     * @param content  Video URL, text content, or comma-separated quiz questions depending on type
     * @return The saved Lesson object
     */
    Lesson save(long courseId, String title, String type, String content) throws SQLException;

    /**
     * Count the number of lessons in a course
     */
    int countByCourse(int courseId) throws SQLException;
}