package platform;

import platform.CourseManagementComponent.Course;
import platform.CourseManagementComponent.CourseService;
import platform.LearningComponent.entity.Lesson;
import platform.LearningComponent.entity.QuizLesson;
import platform.LearningComponent.entity.TextLesson;
import platform.LearningComponent.entity.VideoLesson;
import platform.LearningComponent.service.LessonService;
import platform.LearningComponent.service.ProgressService;
import platform.UserManagementComponent.UserService;
import platform.LearningComponent.repository.LessonRepository;
import platform.CourseManagementComponent.CourseRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner cin = new Scanner(System.in);
        CourseService courseService = new CourseService();
        LessonService lessonService = new LessonService();
        ProgressService progressService = new ProgressService();
        UserService userService = new UserService();

        CourseRepository courseRepo = new CourseRepository();
        LessonRepository lessonRepo = new LessonRepository();

        while (true) {
            System.out.println("""
                    --Welcome to Online Learning platform!--
                    1. Create course
                    2. Create lesson
                    3. Enroll user to course
                    4. Open lesson
                    5. Mark lesson completed
                    6. Show course progress
                    7. Show all courses
                    8. Show active courses with lesson types
                    0. Exit
                    """);

            int choice = cin.nextInt();
            cin.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Course title: ");
                    String title = cin.nextLine();
                    Course c = courseService.createCourse(title);
                    System.out.println("Course created successfully, ID: " + c.getId());
                }
                case 2 -> {
                    System.out.print("Course ID: ");
                    Long courseId = cin.nextLong();
                    cin.nextLine();

                    System.out.print("Lesson title: ");
                    String lessonTitle = cin.nextLine();

                    System.out.print("Lesson type (video/text/quiz): ");
                    String lessonType = cin.nextLine().toLowerCase();

                    String content = "";
                    switch (lessonType) {
                        case "video" -> {
                            System.out.print("Video URL: ");
                            content = cin.nextLine();
                        }
                        case "text" -> {
                            System.out.print("Text content: ");
                            content = cin.nextLine();
                        }
                        case "quiz" -> {
                            System.out.print("Enter quiz questions separated by commas: ");
                            content = cin.nextLine();
                        }
                        default -> {
                            System.out.println("Invalid lesson type!");
                            break;
                        }
                    }

                    Lesson c = lessonService.createLesson(courseId, lessonTitle, lessonType, content);
                    System.out.println("Lesson created successfully!");
                    System.out.println("Course created successfully, Lesson ID: " + c.getId());
                }

                case 3 -> {
                    System.out.print("User ID: ");
                    Long userId = cin.nextLong();
                    System.out.print("Course ID: ");
                    Long courseId = cin.nextLong();
                    courseService.enroll(Math.toIntExact(userId), Math.toIntExact(courseId));
                    System.out.println("User enrolled");
                }
                case 4 -> {
                    System.out.print("User ID: ");
                    Long userId = cin.nextLong();
                    System.out.print("Lesson ID: ");
                    Long lessonId = cin.nextLong();
                    lessonService.openLesson(userId, lessonId);
                    System.out.println("Lesson opened");
                }
                case 5 -> {
                    System.out.print("User ID: ");
                    Long userId = cin.nextLong();
                    System.out.print("Lesson ID: ");
                    Long lessonId = cin.nextLong();
                    progressService.markCompleted(Math.toIntExact(userId), Math.toIntExact(lessonId));
                    System.out.println("Lesson marked as completed");
                }
                case 6 -> {
                    System.out.print("User ID: ");
                    Long userId = cin.nextLong();
                    System.out.print("Course ID: ");
                    Long courseId = cin.nextLong();
                    int progress = (int) progressService.getProgress(Math.toIntExact(userId), Math.toIntExact(courseId));
                    System.out.println("Course progress: " + progress + "%");
                }
                case 7 -> {
                    System.out.println("All courses:");
                    for (Course c : courseService.listAllCourses()) {
                        System.out.println(c.getId() + " | " + c.getTitle() + " | " + c.isArchived());
                    }
                }
                case 8 -> {
                    List<Course> activeCourses = courseService.listAllCourses().stream()
                            .filter(c -> !c.isArchived())
                            .toList();

                    for (Course course : activeCourses) {
                        System.out.println("Course: " + course.getTitle());
                        List<Lesson> lessons = lessonRepo.findByCourseId(course.getId());
                        lessons.forEach(l -> {
                            System.out.print(" - " + l.getTitle() + " (Type: ");
                            if (l instanceof VideoLesson) System.out.println("Video)");
                            else if (l instanceof TextLesson) System.out.println("Text)");
                            else if (l instanceof QuizLesson) System.out.println("Quiz)");
                        });
                    }
                }
                case 0 -> { 
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}
