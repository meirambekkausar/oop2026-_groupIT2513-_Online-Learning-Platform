package platform;

import platform.entity.Course;
import platform.entity.User;
import platform.entity.Lesson;
import platform.repository.CourseRepository;
import platform.repository.LessonRepository;
import platform.service.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner cin = new Scanner(System.in);
        CourseService courseService = new CourseService();
        LessonService lessonService = new LessonService();
        ProgressService progressService = new ProgressService();

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
                    0. Exit
                    """);
            int choice = cin.nextInt();
            cin.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.print("Course title: ");
                    //  cin.nextLine();
                    String title = cin.nextLine();
                    Course fg = courseRepo.save(title);
                    System.out.println("Course created successfully, ID: " + fg.getId());
                }
                case 2 -> {
                    System.out.print("Course ID: ");
                    Long courseId = cin.nextLong();

                    System.out.print("Lesson title: ");
                    cin.nextLine();
                    String lessonTitle = cin.nextLine();
                    lessonRepo.save(courseId, lessonTitle);
                    System.out.println("Lesson created");
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
                    int gh = (int) progressService.getProgress(Math.toIntExact(userId), Math.toIntExact(courseId));

                    System.out.println("Course progress: " + gh + "%");
                }
                case 7 -> {
                    System.out.println("All courses:");
                    for (Course c : courseService.listAllCourses()) {
                        System.out.println(c.getId() + " | " + c.getTitle());
                    }
                }
                case 0 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}
