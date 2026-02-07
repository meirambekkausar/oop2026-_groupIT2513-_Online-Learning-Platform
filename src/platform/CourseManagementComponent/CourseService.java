package platform.CourseManagementComponent;

import java.util.List;

public class CourseService {

    private final CourseRepository courseRepo = new CourseRepository();
    private final EnrollmentRepository enrollRepo = new EnrollmentRepository();

    public void enroll(int userId, int courseId) throws Exception {
        Course course = courseRepo.findById((long) courseId).orElseThrow();
        if (course.isArchived()) throw new CourseArchivedException();
        enrollRepo.save(userId, courseId);
    }

    public Course createCourse(String title) throws Exception {
        return courseRepo.save(new Course.Builder(title).build());
    }

    public List<Course> listAllCourses() throws Exception {
        return courseRepo.findAll();
    }
}
