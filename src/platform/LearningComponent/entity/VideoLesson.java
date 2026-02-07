package platform.LearningComponent.entity;

public class VideoLesson extends Lesson {
    private final String videoUrl;

    public VideoLesson(long id, long courseId, String title, String videoUrl) {
        super(id, courseId, title);
        this.videoUrl = videoUrl;
    }

    @Override
    public void play() {
        System.out.println("Playing video: " + videoUrl);
    }

    public String getVideoUrl() { return videoUrl; }
}
