package pl.zajavka.project_manager.business.dao;

import pl.zajavka.project_manager.domian.User;

import java.util.List;

public interface UsersDAO {

    List<User> findAllUsers();

    User findUserById(Integer userId);

    User saveUser(User user);

    void deleteUser(User user);
}
