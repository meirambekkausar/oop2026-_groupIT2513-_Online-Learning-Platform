package platform.entity;

public class Progress {
    private int userId;
    private int lessonId;
    private boolean completed;

    public Progress(int userId, int lessonId, boolean completed) {
        this.userId = userId;
        this.lessonId = lessonId;
        this.completed = completed;
    }

    public int getUserId() { return userId; }
    public int getLessonId() { return lessonId; }
    public boolean isCompleted() { return completed; }
}
