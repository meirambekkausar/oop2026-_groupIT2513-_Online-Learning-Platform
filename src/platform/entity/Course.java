package platform.entity ;
public class Course {
    private int id;
    private String title;
    private boolean archived;

    public Course(int id, String title, boolean archived) {
        this.id = id;
        this.title = title;
        this.archived = archived;
    }

    public int getId() {
        return id;
    }

    public boolean isArchived() {
        return archived;
    }

    public String getTitle() {
        return title;
    }
}
