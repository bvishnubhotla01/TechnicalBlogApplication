package technicalblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalblog.model.User;
import technicalblog.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(User user) {
        User existingUser = userRepository.checkUser(user.getUsername(), user.getPassword());

        if (existingUser != null) {
            return existingUser;
        } else {
            return null;
        }
    }

    public void registerUser(User newUser) {
        userRepository.registerUser(newUser);
    }
}
