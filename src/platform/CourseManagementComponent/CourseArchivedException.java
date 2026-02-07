package platform.CourseManagementComponent;

public class CourseArchivedException extends RuntimeException {
    public CourseArchivedException() {
        super("Course is archived");
    }
}
