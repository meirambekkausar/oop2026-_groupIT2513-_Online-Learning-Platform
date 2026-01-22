package platform.service;

import platform.entity.Lesson;
import platform.exception.LessonNotFoundException;
import platform.exception.UserNotEnrolledException;
import platform.repository.EnrollmentRepository;
import platform.repository.LessonRepository;

public class LessonService {

    private final LessonRepository lessonRepo = new LessonRepository();
    private final EnrollmentRepository enrollRepo = new EnrollmentRepository();

    public Lesson openLesson(long userId, long lessonId) throws Exception {
        Lesson lesson = lessonRepo.findById(lessonId)
                .orElseThrow(() -> new LessonNotFoundException(lessonId));

        if (!enrollRepo.exists(Math.toIntExact(userId), Math.toIntExact(lesson.getCourseId() ))){
            throw new UserNotEnrolledException();
        }

        return lesson;
    }

    public Lesson createLesson(Long courseId, String title) throws Exception {
        return lessonRepo.save(courseId, title);
    }
}

