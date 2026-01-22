package platform.service;

import platform.entity.User;
import platform.repository.UserRepository;

public class UserService {

    private final UserRepository userRepo = new UserRepository();

    public User createUser(String name, String email) throws Exception {
        return userRepo.save(name, email);
    }
}

