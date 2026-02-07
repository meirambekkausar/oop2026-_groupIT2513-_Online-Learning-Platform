package platform.CourseManagementComponent;

public class Enrollment {
    private int userId;
    private int courseId;

    public Enrollment(int userId, int courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public int getUserId() { return userId; }
    public int getCourseId() { return courseId; }
}
