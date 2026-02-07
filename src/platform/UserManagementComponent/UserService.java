package platform.UserManagementComponent;

public class UserService {

    private final UserRepository userRepo = new UserRepository();

    public User createUser(String name, String email) throws Exception {
        return userRepo.save(name, email);
    }
}
