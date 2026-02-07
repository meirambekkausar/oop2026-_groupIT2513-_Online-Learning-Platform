package platform.service;

import platform.entity.Lesson;
import platform.exception.LessonNotFoundException;
import platform.exception.UserNotEnrolledException;
import platform.CourseManagementComponent.EnrollmentRepository;
import platform.repository.LessonRepository;

import java.sql.SQLException;

public class LessonService {

    private final LessonRepository lessonRepo = new LessonRepository();
    private final EnrollmentRepository enrollRepo = new EnrollmentRepository();

    public Lesson openLesson(long userId, long lessonId) throws Exception {
        Lesson lesson = lessonRepo.findById(lessonId).orElseThrow(() -> new LessonNotFoundException(lessonId));
        if (!enrollRepo.exists(Math.toIntExact(userId), Math.toIntExact(lesson.getCourseId())))
            throw new UserNotEnrolledException();
        return lesson;
    }

    public Lesson createLesson(long courseId, String title, String type, String content) throws SQLException {
        if (!type.equals("video") && !type.equals("text") && !type.equals("quiz")) {
            throw new IllegalArgumentException("Invalid lesson type: " + type);
        }
        return lessonRepo.save(courseId, title, type, content);
    }
}
