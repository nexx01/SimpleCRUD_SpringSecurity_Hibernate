package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    User saveUser(User user);

    void delete(User User);

    List<User> getAllUsers();

    User findUserbyId(Long Id);

    User getUserByLogin(String login);
}
