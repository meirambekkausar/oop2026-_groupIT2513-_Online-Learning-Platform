package platform.exception;


public class UserNotEnrolledException extends RuntimeException {
    public UserNotEnrolledException() {
        super("User is not enrolled in this course");
    }
}
