package platform.LearningComponent.entity;

public class LessonFactory {
    public static Lesson createLesson(String type, long id, long courseId, String title, Object content) {
        return switch (type.toLowerCase()) {
            case "video" -> new VideoLesson(id, courseId, title, (String) content);
            case "text" -> new TextLesson(id, courseId, title, (String) content);
            case "quiz" -> new QuizLesson(id, courseId, title, (String[]) content);
            default -> throw new IllegalArgumentException("Unknown lesson type: " + type);
        };
    }
}
