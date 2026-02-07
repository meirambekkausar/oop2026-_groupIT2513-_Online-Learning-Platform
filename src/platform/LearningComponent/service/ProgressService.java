package platform.LearningComponent.service;

import platform.LearningComponent.repository.LessonRepository;
import platform.LearningComponent.repository.ProgressRepository;

public class ProgressService {

    private final ProgressRepository progressRepo = new ProgressRepository();
    private final LessonRepository lessonRepo = new LessonRepository();

    public void markCompleted(int userId, int lessonId) throws Exception {
        progressRepo.markCompleted(userId, lessonId);
    }

    public double getProgress(int userId, int courseId) throws Exception {
        int completed = progressRepo.countCompleted(userId, courseId);
        int total = lessonRepo.countByCourse(courseId);
        return (double) completed / total * 100;
    }
}
