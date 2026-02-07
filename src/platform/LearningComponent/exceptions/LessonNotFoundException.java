package platform.LearningComponent.exceptions;

public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException(Long lessonId) {
        super("Lesson not found: " + lessonId);
    }
}
