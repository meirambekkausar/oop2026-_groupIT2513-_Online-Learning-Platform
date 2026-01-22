package platform.exception;

public class CourseArchivedException extends RuntimeException {
    public CourseArchivedException() {
        super("Course is archived");
    }
}
