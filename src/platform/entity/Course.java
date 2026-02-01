package platform.entity;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int id;
    private String title;
    private boolean archived;
    private List<Lesson> lessons;

    private Course(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.archived = builder.archived;
        this.lessons = builder.lessons;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public boolean isArchived() { return archived; }

    public static class Builder {
        private int id;
        private final String title;
        private boolean archived = false;
        private List<Lesson> lessons = new ArrayList<>();

        public Builder(String title) { this.title = title; }
        public Builder id(int id) { this.id = id; return this; }
        public Builder archived(boolean archived) { this.archived = archived; return this; }
        public Builder lessons(List<Lesson> lessons) { this.lessons = lessons; return this; }
        public Course build() { return new Course(this); }
    }
}
