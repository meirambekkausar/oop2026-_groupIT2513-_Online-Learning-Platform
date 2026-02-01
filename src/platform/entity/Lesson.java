package platform.entity;

public abstract class Lesson {
    private long id;
    private long courseId;
    private String title;
    private String lessonType;
    private String   content;

    public Lesson(long id, long courseId, String title) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
    }

    public long getId() { return id; }
    public long getCourseId() { return courseId; }
    public String getTitle() { return title; }

    public abstract void play();
}
