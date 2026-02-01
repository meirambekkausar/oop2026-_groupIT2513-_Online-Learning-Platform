package platform.entity;

public class TextLesson extends Lesson {
    private final String textContent;

    public TextLesson(long id, long courseId, String title, String textContent) {
        super(id, courseId, title);
        this.textContent = textContent;
    }

    @Override
    public void play() {
        System.out.println("Reading text: " + textContent);
    }

    public String getTextContent() { return textContent; }
}
