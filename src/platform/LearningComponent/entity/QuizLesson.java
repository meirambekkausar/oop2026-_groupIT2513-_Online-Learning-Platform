package platform.LearningComponent.entity;

public class QuizLesson extends Lesson {
    private final String[] questions;

    public QuizLesson(long id, long courseId, String title, String[] questions) {
        super(id, courseId, title);
        this.questions = questions;
    }

    @Override
    public void play() {
        System.out.println("Starting quiz with " + questions.length + " questions.");
    }

    public String[] getQuestions() { return questions; }
}
