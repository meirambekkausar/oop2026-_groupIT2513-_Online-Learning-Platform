package platform.exception;

public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException(Long lessonId) {
        super("Lesson not found: " + lessonId);
    }
}
