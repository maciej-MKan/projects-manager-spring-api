package pl.zajavka.project_manager.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zajavka.project_manager.business.dao.UsersDAO;
import pl.zajavka.project_manager.domian.User;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UsersService {

    private final UsersDAO usersDAO;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAllUsers() {
        List<User> allUsers = usersDAO.findAllUsers();
        log.info("Found and return [{}] projects", allUsers.size());
        return allUsers;
    }

    public User findUserById(Integer userId) {
        User user = usersDAO.findUserById(userId);
        log.info("Found user with ID [{}]", userId);
        return user;
    }

    public User saveUser(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        User newUser = usersDAO.saveUser(user.withPassword(encodePassword));
        log.info("created new user with ID [{}]", newUser.getUserId());
        return newUser;
    }

    public User updateUser(User user) {
        if (user.getUserId() == null) {
            log.error("Illegal update user [{}]", user);
            throw new RuntimeException();
        }
        User updatedUser = usersDAO.saveUser(user);
        log.info("Updated user with ID [{}]", user.getUserId());
        return updatedUser;
    }

    public void deleteUser(Integer userId) {
        User user = User.builder()
                .userId(userId)
                .build();
        usersDAO.deleteUser(user);
        log.info("Deleted user [{}]", user);
    }
}
